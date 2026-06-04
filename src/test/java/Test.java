import snippets.FeatureSnippetsRunner;

/**
 * Default entry: runs all concrete feature snippets ({@link snippets.FeatureSnippetsRunner}).
 * Index: {@link snippets.SnippetCatalog}. Single topic: run any class {@code main}, e.g.
 * {@code logging.LoggingConcreteDemo}, {@code assertions.JsonSchemaValidationConcreteDemo}.
 */
public class Test {

  public static void main(String[] args) throws Exception {
    if (args.length > 0 && "recap".equals(args[0])) {
      recap.RestAssuredRecapLauncher.main(java.util.Arrays.copyOfRange(args, 1, args.length));
      return;
    }
    FeatureSnippetsRunner.main(args);
  }
}
