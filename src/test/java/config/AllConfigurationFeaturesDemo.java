package config;

/**
 * Runs all REST Assured configuration feature demos in one main.
 */
public class AllConfigurationFeaturesDemo {

  public static void main(String[] args) {
    String[] none = new String[0];
    io.restassured.RestAssured.reset();
    run("LogConfig", () -> LogConfigConcreteDemo.main(none));
    run("SSL", () -> SslConfigConcreteDemo.main(none));
    run("Redirect", () -> RedirectConfigConcreteDemo.main(none));
    run("EncoderDecoder", () -> EncoderDecoderConfigConcreteDemo.main(none));
    run("Parser", () -> ParserConfigConcreteDemo.main(none));
    run("ConnectionHttpClient", () -> ConnectionHttpClientConfigConcreteDemo.main(none));
    run("Session", () -> SessionConfigConcreteDemo.main(none));
    run("ProxyEncoding", () -> ProxyEncodingConcreteDemo.main(none));
    System.out.println("\nAllConfigurationFeaturesDemo — completed.");
  }

  private static void run(String name, Runnable r) {
    System.out.println("\n########## " + name + " ##########");
    r.run();
  }
}
