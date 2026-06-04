package common.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** DummyJSON login response — fields used for Bearer + refresh */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

  public String accessToken;
  public String refreshToken;
  public int id;
  public String username;
  public String email;
  public String firstName;
  public String lastName;
  public String gender;
  public String image;
}
