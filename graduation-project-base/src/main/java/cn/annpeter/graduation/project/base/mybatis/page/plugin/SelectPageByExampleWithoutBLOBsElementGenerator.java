package cn.annpeter.graduation.project.base.mybatis.page.plugin;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * Created on 2017/02/19
 *
 * @author annpeter.it@gmail.com
 */
public class SelectPageByExampleWithoutBLOBsElementGenerator extends
        AbstractXmlElementGenerator {

    public SelectPageByExampleWithoutBLOBsElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        String fqjt = introspectedTable.getExampleType();

        XmlElement answer = new XmlElement("select");

        answer.addAttribute(new Attribute("id",
                (String) introspectedTable.getAttribute("ATTR_SELECT_PAGE_BY_EXAMPLE_STATEMENT_ID")));
        answer.addAttribute(new Attribute(
                "resultMap", introspectedTable.getBaseResultMapId()));
        answer.addAttribute(new Attribute("parameterType", fqjt));

        context.getCommentGenerator().addComment(answer);

        answer.addElement(new TextElement("select"));
        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "example.distinct"));
        ifElement.addElement(new TextElement("distinct"));
        answer.addElement(ifElement);

        StringBuilder sb = new StringBuilder();
        if (stringHasValue(introspectedTable
                .getSelectByExampleQueryId())) {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByExampleQueryId());
            sb.append("' as QUERYID,");
            answer.addElement(new TextElement(sb.toString()));
        }
        answer.addElement(getBaseColumnListElement());

        sb.setLength(0);
        sb.append("from ");
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getUpdateByExampleIncludeElement());

        ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "example.orderByClause != null"));
        ifElement.addElement(new TextElement("order by ${example.orderByClause}"));
        answer.addElement(ifElement);

        if (context.getPlugins()
                .sqlMapSelectByExampleWithoutBLOBsElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
