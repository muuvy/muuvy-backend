package org.muuvy.backend.persistence.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.muuvy.backend.business.rest.dto.FavoriteDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQuery(name = "Favorite.findAll", query = "SELECT t FROM Favorite t")
public class Favorite {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NonNull
	private String movieId;

	@Override
	public String toString() {
		return "Favorite{" +
				"id='" + id + '\'' +
				", movieId='" + movieId + '\'' +
				'}';
	}
}
