package com.xxc.xia.common.utils;

/**
 * Desc...
 *
 * @author xxc
 * @create 2024/8/2 22:56
 */
public class MysqlTableGeneUtils {

    public static String geneBaseTable(String tableName, String tableComment) {
        String sqlTemplate = "CREATE TABLE `${tableName}` (\n"
                             + "  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',\n"
                             + "  `extend_info` longtext COLLATE utf8mb4_general_ci COMMENT '扩展信息',\n"
                             + "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n"
                             + "  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',\n"
                             + "  `update_time` datetime DEFAULT NULL COMMENT '修改时间',\n"
                             + "  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',\n"
                             + "  PRIMARY KEY (`id`)\n"
                             + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='${tableComment}'";
        return sqlTemplate.replace("${tableName}", tableName).replace("${tableComment}",
            tableComment);
    }
}
