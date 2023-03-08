package blackbox.com.anz.ms.accountenquiry.controller;

import com.anz.ms.accountenquiry.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.context.jdbc.Sql.*;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/db/cleanup.sql", "/db/test_data.sql"})
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = {"/db/cleanup.sql"})
public class AccountEnquiryControllerTest {

    @LocalServerPort
    private final int serverPort = 8090;

    private final String API_RETRIEVE_ACCOUNTS = "http://localhost:" + serverPort + "/account-enquiry/users/{userCode}/accounts";
    private final String API_RETRIEVE_TRANSACTIONS = "http://localhost:" + serverPort + "/account-enquiry/accounts/{accountNumber}/transactions";

    @Test
    public void retrieveAccountsForGivenUser2xx() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(API_RETRIEVE_ACCOUNTS, "U0003")
                .then()
                .statusCode(200)
                .body("accountResponseList[0].accountNumber", is("ACCNUMBER_123456"))
                .body("accountResponseList[0].accountName", is("ACCNAME_PERSONAL_1"))
                .body("accountResponseList[0].accountType", is("Savings"))
                .body("accountResponseList[0].balanceDate", is("2023-03-02"))
                .body("accountResponseList[0].currency", is("AUD"))
                .body("accountResponseList[0].openingAvailableBalance", is(6800.57F))
                .body("accountResponseList[0].links", notNullValue())
                .body("accountResponseList[0].links[0].href", is(API_RETRIEVE_TRANSACTIONS.replaceFirst("\\{accountNumber\\}", "ACCNUMBER_123456")))
                .body("accountResponseList[1].accountNumber", is("ACCNUMBER_123457"))
                .body("accountResponseList[1].accountName", is("ACCNAME_PERSONAL_2"))
                .body("accountResponseList[1].accountType", is("Savings"))
                .body("accountResponseList[1].balanceDate", is("2023-03-01"))
                .body("accountResponseList[1].currency", is("AUD"))
                .body("accountResponseList[1].openingAvailableBalance", equalTo(9000.33F))
                .body("accountResponseList[1].links", notNullValue())
                .body("accountResponseList[1].links[0].href", is(API_RETRIEVE_TRANSACTIONS.replaceFirst("\\{accountNumber\\}", "ACCNUMBER_123457")));
    }

    @Test
    public void retrieveTransactionForGivenAccount2xx() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(API_RETRIEVE_TRANSACTIONS, "ACCNUMBER_123457")
                .then()
                .statusCode(200)
                .body("transactionResponseList[0].accountNumber", is("ACCNUMBER_123457"))
                .body("transactionResponseList[0].accountName", is("ACCNAME_PERSONAL_2"))
                .body("transactionResponseList[0].valueDate", is("2023-03-01"))
                .body("transactionResponseList[0].currency", is("AUD"))
                .body("transactionResponseList[0].debitAmount", is(0F))
                .body("transactionResponseList[0].creditAmount", is(100.35F))
                .body("transactionResponseList[0].transactionType", is("Credit"))
                .body("transactionResponseList[0].transactionNarrative", is("test transaction"))
                .body("transactionResponseList[1].accountNumber", is("ACCNUMBER_123457"))
                .body("transactionResponseList[1].accountName", is("ACCNAME_PERSONAL_2"))
                .body("transactionResponseList[1].valueDate", is("2023-03-02"))
                .body("transactionResponseList[1].currency", is("AUD"))
                .body("transactionResponseList[1].debitAmount", is(700.5F))
                .body("transactionResponseList[1].creditAmount", is(0F))
                .body("transactionResponseList[1].transactionType", is("Debit"))
                .body("transactionResponseList[1].transactionNarrative", is("test transaction"))
                .body("_links.accounts.href", is(API_RETRIEVE_ACCOUNTS.replaceFirst("\\{userCode\\}", "U0003")));
    }

    @Test
    public void retrieveAccountsForInvalidUser4xx() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(API_RETRIEVE_ACCOUNTS, "INVALID_USER")
                .then()
                .statusCode(404)
                .body("errorId", is("DATA_NOT_FOUND"))
                .body("message", is("User not found for User Code: INVALID_USER"))
                .body("status", is("NOT_FOUND"));
    }

    @Test
    public void retrieveAccountsForBlankUser4xx() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(API_RETRIEVE_ACCOUNTS, " ")
                .then()
                .statusCode(400)
                .body("errorId", is("USER_PARAM_INVALID"))
                .body("message", is("User code is blank"))
                .body("status", is("BAD_REQUEST"));
    }

    @Test
    public void retrieveTransactionsForInvalidAccount_4xx() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(API_RETRIEVE_TRANSACTIONS, "INVALID_ACCOUNT_NUMBER")
                .then()
                .statusCode(404)
                .body("errorId", is("DATA_NOT_FOUND"))
                .body("message", is("Account not found for Account Number: INVALID_ACCOUNT_NUMBER"))
                .body("status", is("NOT_FOUND"));
    }

    @Test
    public void retrieveTransactionsForBlankAccount_4xx() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(API_RETRIEVE_TRANSACTIONS, " ")
                .then()
                .statusCode(400)
                .body("errorId", is("USER_PARAM_INVALID"))
                .body("message", is("Account number code is blank"))
                .body("status", is("BAD_REQUEST"));
    }
}
