package com.nirengi.kapnews.disclosure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class DisclosureDto {

    private Long id;
    private String disclosureId;
    private String stockCode;
    private String title;
    private String summary;
    private String publishDate;

    public String toDisclosureString() {
        Field[] fields = DisclosureDto.class.getDeclaredFields();
        String dto = "";
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true); // Allow access to private fields
            if (!field.getName().equals("id")) {
                try {
                    dto = dto + field.getName() + " : " + (String) field.get(this) + "\n";
                } catch (Exception e) {
                }
            }
        }
        return dto;
    }

}
