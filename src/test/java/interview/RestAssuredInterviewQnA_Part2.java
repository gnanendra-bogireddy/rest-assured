package interview;

/**
 * REST Assured SME Interview Q&A - Part 2 (Questions 31-60)
 * Topic: Parsing, JsonPath, and XMLPath
 */
public class RestAssuredInterviewQnA_Part2 {

    /*
     * 31. What is JsonPath?
     * Ans: A way to extract and query data from a JSON response.
     * 
     * 32. How do you create a JsonPath object from a Response?
     * Ans: JsonPath js = response.jsonPath();
     * 
     * 33. How do you get a list of values using JsonPath?
     * Ans: List<String> list = js.getList("path.to.array");
     * 
     * 34. What is the syntax to get the first element of an array in JsonPath?
     * Ans: js.get("array[0]");
     * 
     * 35. How do you get the size of an array in JSON response?
     * Ans: js.get("array.size()");
     * 
     * 36. What is Groovy's GPath notation?
     * Ans: A powerful query language used by REST Assured to filter and search JSON/XML.
     * 
     * 37. How do you find a specific object in a list based on a condition?
     * Ans: js.get("list.find { it.name == 'John' }");
     * 
     * 38. What does 'it' represent in GPath expressions?
     * Ans: It represents the current element in the iteration.
     * 
     * 39. How do you get all elements that match a condition?
     * Ans: js.get("list.findAll { it.price > 100 }");
     * 
     * 40. How do you collect specific fields from a filtered list?
     * Ans: js.get("list.findAll { it.price > 100 }.name");
     * 
     * 41. How do you get the max value of a field in a list?
     * Ans: js.get("list.max { it.id }.id");
     * 
     * 42. How do you sum up values in a JSON array?
     * Ans: js.get("list.sum { it.price }");
     * 
     * 43. What is XMLPath?
     * Ans: Similar to JsonPath but used for querying XML responses.
     * 
     * 44. How do you access an attribute in XMLPath?
     * Ans: xmlPath.get("node.@attributeName");
     * 
     * 45. What is the difference between get() and getString() in JsonPath?
     * Ans: get() is generic and tries to infer type, getString() explicitly returns a String.
     * 
     * 46. How do you get an integer value from JsonPath?
     * Ans: js.getInt("path.to.int");
     * 
     * 47. How do you handle deep nested JSON paths?
     * Ans: By using dot notation: "a.b.c.d".
     * 
     * 48. Can you use JsonPath without REST Assured?
     * Ans: Yes, by importing io.rest-assured:json-path dependency.
     * 
     * 49. How do you validate a specific value in a list using hamcrest?
     * Ans: then().body("list.name", hasItem("John"));
     * 
     * 50. How do you validate multiple values in a list?
     * Ans: then().body("list.name", hasItems("John", "Jane"));
     * 
     * 51. What is the syntax to access the last element of an array in GPath?
     * Ans: js.get("array[-1]");
     * 
     * 52. How do you get a subset of an array in GPath?
     * Ans: js.get("array[0..2]"); (gets elements from index 0 to 2).
     * 
     * 53. How do you handle JSON keys with dots in them?
     * Ans: By escaping them with single quotes: js.get("'key.with.dot'");
     * 
     * 54. What is the use of the '$' symbol in JsonPath?
     * Ans: It represents the root of the JSON document (optional in REST Assured).
     * 
     * 55. How do you check if a key exists in a JSON response?
     * Ans: js.get("path.to.key") != null.
     * 
     * 56. Can we parse HTML with REST Assured?
     * Ans: Yes, using HtmlPath.
     * 
     * 57. What is the difference between XmlPath and XPath?
     * Ans: XmlPath is a Groovy-based GPath for XML, while XPath is a standard XML query language.
     * 
     * 58. How do you get the root element name in XML?
     * Ans: xmlPath.get("name()");
     * 
     * 59. How do you extract an object back into a Map?
     * Ans: js.getMap("path.to.object");
     * 
     * 60. How do you validate the size of a list in the response body?
     * Ans: then().body("list.size()", equalTo(5));
     */
}
