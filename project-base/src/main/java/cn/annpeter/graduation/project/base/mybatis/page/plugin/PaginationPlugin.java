package cn.annpeter.graduation.project.base.mybatis.page.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

import java.util.List;

public class PaginationPlugin extends PluginAdapter {

    private static final String mapperId = "selectPageByExample";

    @Override
    public boolean clientGenerated(Interface anInterface, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        String pageRowBoundsClass = getProperties().getProperty("pageRowBoundsClass");
        String returnTypeClass = getProperties().getProperty("returnTypeClass");

        anInterface.addImportedType(new FullyQualifiedJavaType(pageRowBoundsClass));
        anInterface.addImportedType(new FullyQualifiedJavaType(returnTypeClass));

        Method method = new Method();
        method.setName(mapperId);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()),
                "example", "@Param(\"example\")"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType(pageRowBoundsClass), "rowBounds"));

        FullyQualifiedJavaType interfaceReturnType = new FullyQualifiedJavaType(returnTypeClass);
        interfaceReturnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        method.setReturnType(interfaceReturnType);
        anInterface.addMethod(method);

        introspectedTable.setAttribute("ATTR_SELECT_PAGE_BY_EXAMPLE_STATEMENT_ID", mapperId);

        return super.clientGenerated(anInterface, topLevelClass, introspectedTable);
    }


    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        AbstractXmlElementGenerator elementGenerator = new SelectPageByExampleWithoutBLOBsElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, document.getRootElement(), introspectedTable);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private void initializeAndExecuteGenerator(
            AbstractXmlElementGenerator elementGenerator,
            XmlElement parentElement
            , IntrospectedTable introspectedTable) {
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.addElements(parentElement);
    }

    public boolean validate(List<String> warnings) {
        return true;
    }
}
