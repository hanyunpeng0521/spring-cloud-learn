package com.hyp.learn.us.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.us.domain
 * hyp create at 20-1-31
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class DemoResp {

    @ApiModelProperty(name="code",value="编码",example="oKong")
    String code;

    @ApiModelProperty(name="name",value="名称",example="趔趄的猿")
    String name;

    @ApiModelProperty(name="remark",value="备注",example="blog：blog.lqdev.cn")
    String remark;
}
