package org.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author
 * @date 2022/11/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException{
    private Integer code;
    private String msg;
}
