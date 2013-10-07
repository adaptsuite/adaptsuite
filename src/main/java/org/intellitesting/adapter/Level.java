package org.intellitesting.adapter;

public class Level {

	private Long duration;
	private String description;

	public Level(String durationProp) {
		this.description = durationProp;
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

	public String getDescription() {
		return description;
	}

}
