package registrationservice.dev.edgaritzak;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/registrarions")
public class RegistrationController {
	
	private final RegistrationRepository registrationRepo;
	@Autowired
	public RegistrationController(RegistrationRepository registrationRepo) {
		this.registrationRepo = registrationRepo;
	}
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public Registration create(@RequestBody @Valid Registration registration) {
		String ticketCode = UUID.randomUUID().toString();
		return(registrationRepo.save(new Registration(null,registration.productId(), ticketCode, registration.attendeeName())));
	}
	
	@RequestMapping(path = "/{ticketCode}", method = RequestMethod.GET)
	public Registration get(@PathVariable("ticketCode") String ticketCode) {
		return registrationRepo.findByTicketCode(ticketCode)
				.orElseThrow(()-> new NoSuchElementException("Registration with ticket code "+ticketCode+" not found"));
	}
	
	@RequestMapping(path = "/", method = RequestMethod.PUT)
	public Registration update(@RequestBody Registration registration) {
		String ticketCode = registration.ticketCode();
		Registration existing = registrationRepo.findByTicketCode(ticketCode).orElseThrow(()-> new NoSuchElementException("TicketCode "+ ticketCode+" not found"));
		return registrationRepo.save(new Registration(existing.id(),existing.productId(),ticketCode,registration.attendeeName()));
	}
	
	@RequestMapping(path = "/{ticketCode}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String ticketCode) {
		registrationRepo.deleteByTicketCode(ticketCode);
	}
}
