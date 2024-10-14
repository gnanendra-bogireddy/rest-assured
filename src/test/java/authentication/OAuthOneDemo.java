package authentication;

import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import io.restassured.http.ContentType;

public class OAuthOneDemo {
    // Rest assured needs scribe api in classpath to work with OAuth1 authorization.
    // Please add scribejava-apis version 2.4.0 only.
    public static void main(String[] args) {

        // We will use the below values to get authorized for OAuth1 - This will be added Request Headers
        // URI = "https://postman-echo.com/oauth1" Request method = "POST"
        // Content-Type = "multipart/form-data"
        // Consumer Key = "RKCGzna7bv9YD57c"
        // Consumer Secret = "D+EdQ-gs$-%@2Nu7"
        // Access Token = ""
        // Token Secret = ""
        // Signature Method = "HMAC-SHA1"

        // Form Data payload is given below
        // code:xWnkliVQJURqB2x1
        // grant_type:authorization_code
        // redirect_uri:https://www.getpostman.com/oauth2/callback
        // client_id:abc123
        // client_secret:ssh-secret

        RestAssured.baseURI = "https://postman-echo.com";
        RestAssured.basePath = "/oauth1";

        RestAssured.given().log().all()
                .auth().oauth("RKCGzna7bv9YD57c", "D+EdQ-gs$-%@2Nu7", "", "", OAuthSignature.HEADER)
                .contentType(ContentType.MULTIPART)
                .multiPart("code", "xWnkliVQJURqB2x1")
                .multiPart("grant_type", "authorization_code")
                .multiPart("redirect_uri", "https://www.getpostman.com/oauth2/callback")
                .multiPart("client_id", "abc123")
                .multiPart("client_secret", "ssh-secret")
                .get().then().log().all();

    }
}
