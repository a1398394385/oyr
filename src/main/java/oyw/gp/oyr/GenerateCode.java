package oyw.gp.oyr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GenerateCode {
    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());

        if (scanner.hasNext()) {
            String ipt = scanner.next();

            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        /* 代码生成器 */
        AutoGenerator ag = new AutoGenerator();

        /* 全局配置 */
        GlobalConfig config = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        config.setActiveRecord(true) // 是否支持AR模式
                .setOutputDir(projectPath + "/src/main/java") // 生成路径
                .setAuthor("OuYangWei") // 作者
                .setOpen(false) // 是否打开输出目录
                .setFileOverride(true)// 是否文件覆盖
                .setBaseResultMap(true) // XML是否需要BaseResultMap
                .setBaseColumnList(true) // XML是否显示baseColumnList
                .setControllerName("%sController").setServiceName("%sService").setServiceImplName("%sServiceImpl")
                .setMapperName("%sMapper").setXmlName("%sMapper").setIdType(IdType.AUTO); // 主键策略
        ag.setGlobalConfig(config);

        /* 包名策略 */
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("oyw.gp." + scanner("模块名")); // 父包名
        ag.setPackageInfo(pkConfig);

        /* 数据源配置 */
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL).setUrl("jdbc:mysql://106.14.96.160:3306/oyr?serverTimezone=UTC")
                .setDriverName("com.mysql.cj.jdbc.Driver").setUsername("root").setPassword("xihadajiang");
        ag.setDataSource(dsConfig);

        /* 策略配置 */
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setTablePrefix("").setEntityLombokModel(true).setRestControllerStyle(true)
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setEntityTableFieldAnnotationEnable(true).setInclude(scanner("表名，多个英文逗号分割").split(",")); // 需要包含的表名
        ag.setStrategy(stConfig);

        /* 模板设置 */
        TemplateConfig tpConfig = new TemplateConfig();
        tpConfig.setXml(null);
        ag.setTemplate(tpConfig);

        /* 自定义配置 */
        InjectionConfig userConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // String templatePath = "/templates/mapper.xml.ftl"; // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.vm"; // 如果模板引擎是 velocity

        // 自定义 Xml 输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper"
                        + StringPool.DOT_XML;
            }
        });
        userConfig.setFileOutConfigList(focList);
        ag.setCfg(userConfig);

        /* 生成代码 */
        ag.execute();
    }
}