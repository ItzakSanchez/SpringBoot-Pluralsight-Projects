package registrationservice.dev.edgaritzak;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Document("registrations")
public record Registration(
		@Id String id,
		@NotNull(message = "product id must be provided") Integer productId,
		String ticketCode,
		@NotBlank(message = "attendee name cannot be blank") String attendeeName) {

}
