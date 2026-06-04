package common;

/**
 * Public dummy APIs used across snippets (no API keys required unless noted).
 * <ul>
 *   <li>JSONPlaceholder — CRUD JSON (fake persistence)</li>
 *   <li>DummyJSON — auth, products, users (real login/token)</li>
 *   <li>httpbin — auth echo, forms, multipart, status codes, XML/HTML</li>
 *   <li>RESTful API Dev — objects CRUD</li>
 * </ul>
 */
public final class TestApis {

  private TestApis() {}

  public static final String JSON_PLACEHOLDER = "https://jsonplaceholder.typicode.com";
  public static final String DUMMY_JSON = "https://dummyjson.com";
  public static final String HTTPBIN = "https://httpbin.org";
  public static final String RESTFUL_API_DEV = "https://api.restful-api.dev";

  /** DummyJSON demo user (from official docs) */
  public static final String DUMMYJSON_USER = "emilys";
  public static final String DUMMYJSON_PASS = "emilyspass";

  /** httpbin basic auth */
  public static final String HTTPBIN_BASIC_USER = "user";
  public static final String HTTPBIN_BASIC_PASS = "passwd";
}
