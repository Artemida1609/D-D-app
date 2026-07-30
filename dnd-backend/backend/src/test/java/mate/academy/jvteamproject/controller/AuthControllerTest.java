package mate.academy.jvteamproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.jvteamproject.config.TestContainersConfig;
import mate.academy.jvteamproject.dto.DropboxUploadResponse;
import mate.academy.jvteamproject.dto.user.UserRegistrationRequestDto;
import mate.academy.jvteamproject.service.dropbox.DropboxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import static mate.academy.jvteamproject.helper.TestDataHelper.createUserRegistrationRequestDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest extends TestContainersConfig {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DropboxService dropboxService;

    @Test
    public void register_success() throws Exception {
        MockMultipartFile avatar = new MockMultipartFile(
                "avatar",
                "avatar.png",
                MediaType.IMAGE_PNG_VALUE,
                "fake-image-content".getBytes()
        );

        String json = """
                {
                    "email": "new@gmail.com",
                    "password": "123456",
                    "repeatPassword": "123456",
                    "userNickname": "nickname"
                }
                """;

        MockMultipartFile data = new MockMultipartFile(
                "data",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                json.getBytes()
        );

        DropboxUploadResponse mockResponse = new DropboxUploadResponse();
        mockResponse.setFileId("123");
        mockResponse.setImageUrl("http://dropbox.com/fake-url");

        when(dropboxService.upload(any(MultipartFile.class), anyString()))
                .thenReturn(mockResponse);

        mockMvc.perform(multipart("/auth/registration")
                        .file(avatar)
                        .file(data)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("new@gmail.com"));
    }
}
