package eventservice.dev.edgaritzak;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class EventController {
	private final OrganizerRepository organizerRepo;
	private final EventRepository eventRepo;
	private final ProductRepository productRepo;
	
	@Autowired
	public EventController(OrganizerRepository organizerRepo, EventRepository eventRepo,
			ProductRepository productRepo) {
		this.organizerRepo = organizerRepo;
		this.eventRepo = eventRepo;
		this.productRepo = productRepo;
	}
	
	@RequestMapping(path = "/organizers", method = RequestMethod.GET)
	public List<Organizer> getOrganizers(){
		return organizerRepo.findAll();
	}
	
	@RequestMapping(path = "/events", method = RequestMethod.GET)
	public List<Event> getEventsbyOrganizer(@RequestParam("organizerId") int organizerId){
		return eventRepo.findByOrganizerId(organizerId);
	}
	
	@RequestMapping(path = "/events/{id}", method = RequestMethod.GET)
	public Event getEventById(@PathVariable("id") int eventId) {
		return eventRepo.findById(eventId).orElseThrow(()-> new NoSuchElementException("Event with id "+eventId+" not found"));
	}
	@RequestMapping(path = "/products", method = RequestMethod.GET)
	public List<Product> getProductsByEvent(@RequestParam("eventId") int eventId){
		return productRepo.findByEventId(eventId);
	}
	
}
