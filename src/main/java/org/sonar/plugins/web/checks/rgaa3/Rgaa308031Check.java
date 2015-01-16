/*
 * SonarQube Web Plugin
 * Copyright (C) 2010 SonarSource and Matthijs Galesloot
 * dev@sonar.codehaus.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sonar.plugins.web.checks.rgaa3;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.web.checks.AbstractPageCheck;
import org.sonar.plugins.web.checks.WebRule;
import org.sonar.plugins.web.node.DirectiveNode;
import org.sonar.plugins.web.node.Node;
import org.sonar.plugins.web.node.NodeType;
import org.sonar.plugins.web.node.TagNode;

import java.util.List;

@Rule(key = "Rgaa308031Check", priority = Priority.MAJOR)
@WebRule(activeByDefault = true)
public class Rgaa308031Check extends AbstractPageCheck {

  private boolean foundDoctype;
  private boolean reported;

  @Override
  public void startDocument(List<Node> nodes) {
    foundDoctype = true;
    reported = false;
    for (Node node : nodes) {
      if (node.getNodeType().equals(NodeType.DIRECTIVE)) {
        foundDoctype = false;
      }
    }
  }

  @Override
  public void directive(DirectiveNode node) {
    if (isDoctype(node)) {
      foundDoctype = true;
    }
  }

  private static boolean isDoctype(DirectiveNode node) {
    return "DOCTYPE".equalsIgnoreCase(node.getNodeName());
  }

  @Override
  public void startElement(TagNode node) {
    if (isHtml(node) && !foundDoctype && !reported) {
      createViolation(node.getStartLinePosition(), "8.1.3 Pour chaque page Web possédant une déclaration de type de document, celle-ci est-elle située avant la balise html dans le code source ?");
      reported = true;
    }
  }

  private static boolean isHtml(TagNode node) {
    return "HTML".equalsIgnoreCase(node.getNodeName());
  }

}
