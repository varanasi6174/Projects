package com.sipl.vehicle.management.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sipl.vehicle.management.entity.Vehicle;
import com.sipl.vehicle.management.exception.CustomDataIntegrityException;
import com.sipl.vehicle.management.exception.CustomException;
import com.sipl.vehicle.management.exception.VehicleIdNotFoundException;
import com.sipl.vehicle.management.mapper.VehicleMapper;
import com.sipl.vehicle.management.repository.VehicleRepository;
import com.sipl.vehicle.management.service.VehicleService;
import com.sipl.vehicle.management.utils.QRCodeGenerator;
import com.sipl.vehicle.management.vehicleDTO.VehicleDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletResponse;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

	private final VehicleRepository vehiclerepo;
	private final EntityManager manager;
	private final RestTemplate restTemplate;
	private final VehicleMapper vehicleMapper;

	@Autowired
	public VehicleServiceImpl(VehicleRepository vehiclerepo, EntityManager manager, RestTemplate restTemplate,
			VehicleMapper vehicleMapper) {
		this.vehiclerepo = vehiclerepo;
		this.manager = manager;
		this.restTemplate = restTemplate;
		this.vehicleMapper = vehicleMapper;
	}

	@Override
	public Vehicle createVehicle(VehicleDTO vehicleDto) {
		try {
			Vehicle vehicle = vehicleMapper.mapVehicleDTOToVehicle(vehicleDto);
			if (vehiclerepo.existsByVehicleRegistrationNumber(vehicle.getVehicleRegistrationNumber())) {
				throw new VehicleIdNotFoundException("Vehicle with this registration number already exists");
			}
			String UniqueQRCode=QRCodeGenerator.createUniqueNoForQrCode(vehicle);
			vehicle.setQrCode(UniqueQRCode);
			vehicle.setQrImage(QRCodeGenerator.getQRCodeImage(UniqueQRCode, 100, 100));

			return vehiclerepo.save(vehicle);
		} catch (DataIntegrityViolationException ex) {
			throw new CustomDataIntegrityException("Error occurred while saving the vehicle: " + ex.getMessage());
		} catch (VehicleIdNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			throw new CustomException("An unexpected error occurred while creating the vehicle: " + e.getMessage());
		}
	}

	@Override
	@Cacheable(cacheNames = "vehicle")
	public Vehicle getVehicleById(Long id) {
		Vehicle vehicle = null;
		try {
			vehicle = vehiclerepo.findById(id)
					.orElseThrow(() -> new VehicleIdNotFoundException("Vehicle with ID " + id + " not found"));
		} catch (VehicleIdNotFoundException ex) {
			throw ex;
		}
		System.out.println("Vehicle ID collected by DB");

		return vehicle;
	}

	@Override
	@Cacheable(cacheNames = "vehicle")
	public List<Vehicle> getAllVehicle() {
		List<Vehicle> vehicles = vehiclerepo.findAll();
		if (vehicles.isEmpty()) {
			throw new VehicleIdNotFoundException("No vehicles found in the database.");
		}
		System.out.println("Retrieved all vehicles successfully.");
		return vehicles;
	}

	// Using RestTemplate
	@Override
	public String getVehiclebyTemplet() {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<String> templateData = restTemplate.exchange("https://jsonplaceholder.typicode.com/todos/1",
					HttpMethod.GET, entity, String.class, headers);

			return templateData.getBody();
		} catch (Exception e) {

		}
		return null;

	}

//  pagination 

	public Page<Vehicle> getAllVehicle(int pageNo, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		Page<Vehicle> vehicles = vehiclerepo.findAll(pageRequest);

		if (vehicles.isEmpty()) {
			throw new VehicleIdNotFoundException("No vehicles found in the database.");
		}

		System.out.println("Retrieved all vehicles successfully.");
		return vehicles;
	}

	// pagination and sorting

	public Page<Vehicle> getAllVehicle(int pageNo, int pageSize, String sortBy) {
		Sort sort = Sort.by(sortBy).ascending();
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
		Page<Vehicle> vehicles = vehiclerepo.findAll(pageRequest);

		if (vehicles.isEmpty()) {
			throw new VehicleIdNotFoundException("No vehicles found in the database.");
		}

		System.out.println("Retrieved all vehicles successfully.");
		return vehicles;
	}

	@Override
	public Vehicle updateVehicle(Long id, VehicleDTO vehicleDto) {
		Vehicle updatedVehicle = vehiclerepo.findById(id)
				.orElseThrow(() -> new VehicleIdNotFoundException("Vehicle Id not found"));
		updatedVehicle.setOwnerName(vehicleDto.getOwnerName());
		return vehiclerepo.save(updatedVehicle);
	}

	@Override
	public void deleteVehicle(Long id) {
		Vehicle vehicleToDelete = vehiclerepo.findById(id)
				.orElseThrow(() -> new VehicleIdNotFoundException("Vehicle Id not found"));

		vehiclerepo.delete(vehicleToDelete);
	}

	// create pdf
	public ResponseEntity<String> export(HttpServletResponse response) {
		String filePath = "M:\\Pdf generate\\vehicle_management.pdf";
		try {
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			// PdfWriter.getInstance(document, response.getOutputStream());
			document.open();

			Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
			Paragraph titleParagraph = new Paragraph("VEHICLE MANAGEMENT", fontTitle);
			titleParagraph.setAlignment(Element.ALIGN_CENTER);

			Font fontTableHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
			Font fontTableCell = FontFactory.getFont(FontFactory.HELVETICA, 8);
			PdfPTable table = new PdfPTable(10);

			// Add table header
			table.addCell(new PdfPCell(new Phrase("ID", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Registration Number", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Owner Name", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Brand", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Registration Expires", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Is Active", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Created By", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Creation Time", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Modified By", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Modified Time", fontTableHeader)));

			List<Vehicle> vehicles = vehiclerepo.findAll();

			for (Vehicle vehicle : vehicles) {
				// Add table rows
				table.addCell(String.valueOf(vehicle.getId()));
				table.addCell(vehicle.getVehicleRegistrationNumber());
				table.addCell(vehicle.getOwnerName());
				table.addCell(vehicle.getBrand());
				table.addCell(String.valueOf(vehicle.getRegistrationExpires()));
				table.addCell(String.valueOf(vehicle.isActive()));
				table.addCell(vehicle.getCreatedBy());
				table.addCell(String.valueOf(vehicle.getCreationTime()));
				table.addCell(vehicle.getModifiedBy());
				table.addCell(String.valueOf(vehicle.getModifiedTime()));
			}

			document.add(titleParagraph);
			document.add(table);

			document.close();
			return ResponseEntity.ok("PDF exported successfully");
		} catch (DocumentException | IOException e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("PDF export failed");
		}
	}

	// create criteria

	@Override
	public List<Vehicle> searchVehicles(String vehicleRegistrationNumber, String ownerName, String brand) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
		Root<Vehicle> root = query.from(Vehicle.class);

		List<Predicate> predicates = new ArrayList<>();

		if (vehicleRegistrationNumber != null && !vehicleRegistrationNumber.isEmpty()) {
			predicates.add(builder.like(root.get("vehicleRegistrationNumber"), "%" + vehicleRegistrationNumber + "%"));
		}

		if (ownerName != null && !ownerName.isEmpty()) {
			predicates.add(builder.like(root.get("ownerName"), "%" + ownerName + "%"));
		}

		if (brand != null && !brand.isEmpty()) {
			predicates.add(builder.like(root.get("brand"), "%" + brand + "%"));
		}

		query.select(root).where(predicates.toArray(new Predicate[0]));

		return manager.createQuery(query).getResultList();
	}

	
}
