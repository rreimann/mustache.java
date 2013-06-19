package mustachejava;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Example {

  List<Issue> issues() throws URISyntaxException {
    return Arrays.asList(
            new Issue(68l, new URI("http://notmyissues.herokuapp.com/services/issues/68"), "Code Quality", "a short summary", Priority.LOW),
            new Issue(50l, new URI("http://notmyissues.herokuapp.com/services/issues/50"), "Bug", "a short bug summary", Priority.HIGH)
    );
  }

  static class Issue {

      Long id;

      Issue(Long id, URI issueDetailUri, String issueType, String summary, Priority priority) {
          this.id = id;
          this.issueDetailUri = issueDetailUri;
          this.issueType = issueType;
          this.summary = summary;
          this.priority = priority;
      }

      URI issueDetailUri;
      String issueType;
      String summary;
      Priority priority;

  }

  static class Feature {
    Feature(String description) {
      this.description = description;
    }

    String description;
  }

  enum Priority {
      LOW,
      MEDIUM,
      HIGH
  }

  public static void main(String[] args) throws IOException {
    MustacheFactory mf = new DefaultMustacheFactory();
    Mustache mustache = mf.compile("issues.mustache");
    mustache.execute(new PrintWriter(new File("/Users/rr/develop/projects/remote/git/mustache.java/example/target/classes/issues.html")), new Example()).flush();
  }
}
