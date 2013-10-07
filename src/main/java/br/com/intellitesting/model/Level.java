package br.com.intellitesting.model;

public class Level {

	private String description;
	private Long duration;

	public Level(String description, String durationProp) {
		this.description = description;
		this.duration = toMiliseconds(durationProp);
	}

	private Long toMiliseconds(String durationProp) {
		if(durationProp.endsWith("min")){
			Long seconds = Long.valueOf(durationProp.split("min")[0]);
			return seconds * 60 * 1000 ;
		}
		else if(durationProp.endsWith("sec")){
			Long seconds = Long.valueOf(durationProp.split("sec")[0]);
			return seconds * 1000;
		}else{
			return Long.valueOf(durationProp);
		}
	}
	
	public Long getDuration() {
		return duration;
	}

}
