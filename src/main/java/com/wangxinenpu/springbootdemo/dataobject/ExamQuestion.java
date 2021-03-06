 
package com.wangxinenpu.springbootdemo.dataobject;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;

import java.lang.String;
import java.lang.Integer;
import java.util.Date;
import java.util.List;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ExamQuestion implements Serializable{


	//========== properties ==========

	@Id
	@GeneratedValue(generator="JDBC")
    @ApiModelProperty("")
    @Column( name="id")
    private Long id;

    @ApiModelProperty("")
    @Column( name="question_id")
    private Long questionId;

    @ApiModelProperty("")
    @Column( name="exam_id")
    private Long examId;

    @ApiModelProperty("")
    @Column( name="create_time")
    private Date createTime;




}
