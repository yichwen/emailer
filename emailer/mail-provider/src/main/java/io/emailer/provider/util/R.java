/*
 * Copyright (c) 2022. UnitFour Sdn Bhd.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by yichwenlim@gmail.com
 */

package io.emailer.provider.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> {

    private int code;
    private String message;
    private T data;

    public static <T> R<T> ok(int code) {
        return new R<T>().code(code);
    }

    public static <T> R<T> ok() {
        return new R<T>().code(200);
    }

    public static <T> R<T> error() { return new R<T>().code(500).message("error"); }

    public static <T> R<T> error(String message) { return new R<T>().code(500).message(message); }

    public static <T> R<T> error(int code, String message) { return new R<T>().code(code).message(message); }

    public R<T> code(int code) {
        this.code = code;
        return this;
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

    public R<T> message(String message) {
        this.message = message;
        return this;
    }

}
