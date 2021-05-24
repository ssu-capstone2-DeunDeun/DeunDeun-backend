package kr.co.deundeun.groopy.controller.user;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public class UserDocumentation {

    public static RestDocumentationResultHandler signup() {
        return document("회원-가입",
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
                        fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("전화번호"),
                        fieldWithPath("userImageUrl").type(JsonFieldType.STRING).description("유저이미지url")
                ),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer 인증 토큰"))
        );
    }

    public static RestDocumentationResultHandler isDuplicatedNickname() {
        return document("닉네임-중복-체크",
                requestParameters(
                        parameterWithName("nickname").description("닉네임")
                )
        );
    }

    public static RestDocumentationResultHandler changeNickname() {
        return document("닉네임-변경",
                pathParameters(
                        parameterWithName("nickname").description("새로운닉네임")
                ),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer 인증 토큰"))

        );
    }

    public static RestDocumentationResultHandler getUserInfo() {
        return document("회원-정보",
                pathParameters(
                        parameterWithName("nickname").description("닉네임")
                ),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer 인증 토큰"))

        );
    }

    public static RestDocumentationResultHandler addHashtags() {
        return document("회원-해시태그-추가",
                pathParameters(
                        parameterWithName("nickname").description("닉네임")
                ),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer 인증 토큰"))

        );
    }

    public static RestDocumentationResultHandler getHashtags() {
        return document("회원-해시태그-정보",
                pathParameters(
                        parameterWithName("nickname").description("닉네임")
                ),
                requestHeaders(
                        headerWithName("Authorization").description("Bearer 인증 토큰"))

        );
    }
}
