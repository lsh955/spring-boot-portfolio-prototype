package com.ordinary.repository.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 이승환
 * @since 2020-07-22
 */
@Getter
@NoArgsConstructor
public class SlackBotDto {

	private String fallback;
	private String color;
	private String pretext;
	private String author_name;
	private String author_link;
	private String author_icon;
	private String title;
	private String title_link;
	private String text;
	private String image_url;
	private String thumb_url;
	private String footer;
	private String footer_icon;
	private Long ts;
	private List<Field> fields;

}
