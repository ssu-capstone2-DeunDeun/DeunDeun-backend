package kr.co.deundeun.groopy.controller.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

public class UserDocumentation {

    public static RestDocumentationResultHandler signup() {
        return document("user/signup",
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
                        fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("전화번호")
                ),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer 인증 토큰"))
                );
    }
}
