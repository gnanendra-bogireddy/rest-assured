package recap;

/**
 * Entry point for the REST Assured interview recap project.
 * <p>
 * Run from IDE: right-click → Run 'RestAssuredRecapLauncher.main()'
 * Run from Maven:
 * <pre>
 *   mvn -q test-compile exec:java -Dexec.mainClass=recap.RestAssuredRecapLauncher
 *   mvn -q test-compile exec:java -Dexec.mainClass=recap.RestAssuredRecapLauncher -Dexec.args="dsl"
 * </pre>
 * <p>
 * Package map (read in this order for interview prep):
 * <ul>
 *   <li>{@link DslAndHttpMethodsRecap} — given/when/then, static shortcuts, all HTTP verbs</li>
 *   <li>{@link RequestBuildingRecap} — params, headers, cookies, body, forms, multipart</li>
 *   <li>{@link ResponseExtractionRecap} — Response, extract(), JsonPath, POJO mapping</li>
 *   <li>{@link AssertionsAndValidationRecap} — Hamcrest, rootPath, schema, time</li>
 *   <li>{@link SpecificationsAndReuseRecap} — RequestSpec/ResponseSpec, builders, filters</li>
 *   <li>{@link ConfigurationRecap} — SSL, proxy, session, encoder, redirect, parser</li>
 *   <li>{@link AuthenticationRecap} — basic, bearer, preemptive</li>
 *   <li>{@link LoggingRecap} — log().all(), filters, validation-failure logging</li>
 * </ul>
 * Folder-based demos: {@code http.*}, {@code authentication.*}, {@code parsing.*}, {@code config.*},
 * {@code filters.*}, {@code interview.RestAssuredInterviewQnA_Part*} (Q&amp;A comments only).
 */
public class RestAssuredRecapLauncher {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            printMenu();
            runAllRecapDemos();
            return;
        }

        switch (args[0].toLowerCase()) {
            case "dsl" -> DslAndHttpMethodsRecap.main(args);
            case "request" -> RequestBuildingRecap.main(args);
            case "response" -> ResponseExtractionRecap.main(args);
            case "assert" -> AssertionsAndValidationRecap.main(args);
            case "spec" -> SpecificationsAndReuseRecap.main(args);
            case "config" -> ConfigurationRecap.main(args);
            case "all" -> runAllRecapDemos();
            default -> {
                System.err.println("Unknown demo: " + args[0]);
                printMenu();
            }
        }
    }

    private static void runAllRecapDemos() throws Exception {
        System.out.println("\n========== DSL & HTTP ==========\n");
        DslAndHttpMethodsRecap.main(new String[0]);
        System.out.println("\n========== REQUEST BUILDING ==========\n");
        RequestBuildingRecap.main(new String[0]);
        System.out.println("\n========== RESPONSE & EXTRACTION ==========\n");
        ResponseExtractionRecap.main(new String[0]);
        System.out.println("\n========== ASSERTIONS ==========\n");
        AssertionsAndValidationRecap.main(new String[0]);
        System.out.println("\n========== SPECIFICATIONS ==========\n");
        SpecificationsAndReuseRecap.main(new String[0]);
        System.out.println("\n========== CONFIGURATION ==========\n");
        ConfigurationRecap.main(new String[0]);
    }

    private static void printMenu() {
        System.out.println("""
                REST Assured Recap — pass a demo name or run with no args for ALL:
                  dsl      — Given-When-Then, static API, GET/POST/PUT/PATCH/DELETE/HEAD/OPTIONS
                  request  — path/query/form params, headers, cookies, bodies
                  response — extract(), as(), JsonPath, chaining
                  assert   — status, body, Hamcrest, rootPath, JSON Schema
                  spec     — RequestSpec/ResponseSpec, builders, custom filters
                  config   — SSL, proxy, session, encoder, redirects
                  all      — same as no args
                """);
    }
}
