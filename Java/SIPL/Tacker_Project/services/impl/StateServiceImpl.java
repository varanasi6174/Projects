package com.sipl.tracker_rest_repo.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sipl.tracker_rest_repo.dao.entities.AuditEntity;
import com.sipl.tracker_rest_repo.dao.entities.StateMaster;
import com.sipl.tracker_rest_repo.dao.repositories.StateRepository;
import com.sipl.tracker_rest_repo.dto.StateDto;
import com.sipl.tracker_rest_repo.dto.response.StateApiResponse;
import com.sipl.tracker_rest_repo.mappers.StateMapper;
import com.sipl.tracker_rest_repo.services.StateService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StateServiceImpl implements StateService {

	 @PersistenceContext
	  private EntityManager entityManager;

	private final StateRepository repository;
	private final StateMapper mapper;

	@Autowired
	public StateServiceImpl(StateRepository repository, StateMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public StateApiResponse getStateById(Integer id) {
		try {
			log.info("Finding StateMaster with ID: {}", id);
			Optional<StateMaster> stateOptional = repository.findById(id);
			if (stateOptional.isPresent()) {
				StateMaster entity = stateOptional.get();
				StateDto stateDto = mapper.mapStateEntityToStateDto(entity);
				log.info("StateMaster found with ID: {}", id);
				return new StateApiResponse(stateDto, null, null, HttpStatus.OK, "StateMaster found", true);
			} else {
				log.warn("StateMaster not found with ID: {}", id);
				return new StateApiResponse(null, null, null, HttpStatus.NOT_FOUND, "StateMaster not found", false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", id, e);
		} catch (Exception e) {
			log.error("An error occurred while finding StateMaster with ID: {}", id, e);
		}
		return new StateApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while finding StateMaster", false);

	}

	@Override
	public StateApiResponse getAllStateData() {
		try {
			log.info("Finding all StateMaster");
			List<StateMaster> stateMasterList = repository.findAllActive();
			if (stateMasterList.isEmpty()) {
				log.warn("No StateMaster found in the database");
				return new StateApiResponse(null, null, null, HttpStatus.NOT_FOUND, "No StateMaster found", false);
			} else {
				List<StateDto> stateDtoList = stateMasterList.stream().map(mapper::mapStateEntityToStateDto)
						.collect(Collectors.toList());
				log.info("StateMaster retrieved successfully");
				return new StateApiResponse(null, stateDtoList, null, HttpStatus.OK,
						"StateMaster retrieved successfully", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", e);
		} catch (Exception e) {
			log.error("An error occurred while retrieving StateMaster", e);
		}
		return new StateApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while retrieving StateMaster", false);

	}

	@Override
	public StateApiResponse addStateData(StateDto stateDto) {
		try {
			log.info("Saving StateMaster");
			StateMaster entity = mapper.mapStateDtoToStateEntity(stateDto);
			entity.setIsDeleted(false);
			entity.setIsActive(true);
			entity = repository.save(entity);
			StateDto savedDto = mapper.mapStateEntityToStateDto(entity);
			log.info("StateMaster saved successfully");
			return new StateApiResponse(savedDto, null, null, HttpStatus.CREATED, "StateMaster saved successfully",
					true);
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", e);
		} catch (Exception e) {
			log.error("An error occurred while saving StateMaster", e);
		}
		return new StateApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while saving StateMaster", false);

	}

	@Override
	public StateApiResponse deleteStateById(Integer id) {
		try {
			log.info("Deleting StateMaster with ID: {}", id);
			Optional<StateMaster> optionalEntity = repository.findById(id);
			if (optionalEntity.isPresent()) {
				StateMaster stateMaster = optionalEntity.get();
				stateMaster.setIsDeleted(true);
				stateMaster.setIsActive(false);
				repository.save(stateMaster);
				log.info("Updated StateMaster with ID: {}, active set to false", id);
				StateDto deletedDto = mapper.mapStateEntityToStateDto(stateMaster);
				log.info("StateMaster deleted successfully with ID: {}", id);
				return new StateApiResponse(deletedDto, null, null, HttpStatus.OK, "StateMaster deleted successfully",
						true);
			} else {
				log.warn("StateMaster not found with ID: {}", id);
				return new StateApiResponse(null, null, null, HttpStatus.NOT_FOUND, "StateMaster not found", false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", id, e);
		} catch (Exception e) {
			log.error("An error occurred while deleting StateMaster", e);
		}
		return new StateApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while deleting StateMaster", false);

	}

	@Override
	public StateApiResponse updateStateData(StateDto stateDto) {
		try {
			log.info("Updating StateMaster with ID: {}", stateDto.getStateId());
			Optional<StateMaster> optionalEntity = repository.findById(stateDto.getStateId());
			if (optionalEntity.isPresent()) {
				StateMaster existingEntity = mapper.mapStateDtoToStateEntity(stateDto);
				existingEntity.setIsDeleted(false);
				existingEntity.setIsActive(true);
				existingEntity.setStateId(optionalEntity.get().getStateId());
				StateMaster updateStateMaster = repository.save(existingEntity);
				StateDto updatedDto = mapper.mapStateEntityToStateDto(updateStateMaster);
				log.info("StateMaster updated successfully with ID: {}", stateDto.getStateId());
				return new StateApiResponse(updatedDto, null, null, HttpStatus.OK, "StateMaster updated successfully",
						true);
			} else {
				log.warn("StateMaster not found with ID: {}", stateDto.getStateId());
				return new StateApiResponse(stateDto, null, null, HttpStatus.NOT_FOUND, "StateMaster not found", false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", e);
		} catch (Exception e) {
			log.error("An error occurred while updating StateMaster", e);
		}
		return new StateApiResponse(stateDto, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while updating StateMaster", false);

	}

	@Override
	public StateApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize) {
		try {
			log.info("Retrieving StateMaster with pagination");
			int page = pageNum.orElse(0);
			int size = pageSize.orElse(10);
			Pageable pageable = PageRequest.of(page, size);
			Page<StateMaster> stateMasterPage = repository.findAllPagination(pageable);
			if (stateMasterPage.isEmpty()) {
				log.warn("No StateMaster found in the database for the given page");
				return new StateApiResponse(null, null, null, HttpStatus.NOT_FOUND,
						"No StateMaster found in the database for the given page", false);
			} else {
				List<StateDto> dtos = stateMasterPage.getContent().stream().map(mapper::mapStateEntityToStateDto)
						.collect(Collectors.toList());
				log.info("StateMaster retrieved successfully with pagination");
				return new StateApiResponse(null, dtos, null, HttpStatus.OK,
						"StateMaster retrieved successfully with pagination", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Error occurred while finding LocationMaster with ID: {}", e);
		} catch (Exception e) {
			log.error("An error occurred while retrieving StateMaster with pagination", e);
		}
		return new StateApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
				"An error occurred while retrieving StateMaster with pagination", false);

	}

	@Override
	public StateApiResponse generateAndSaveExcel(Integer stateId, String stateName) {
	    // Specify the file path where the Excel file will be saved
	    String filePath = "M:\\SIPL Project\\TRACKER_PROJECT\\states.xlsx";
	    try {
	        // Log the start of Excel generation process
	        log.info("Generating and saving Excel");
	        // Define criteria builder
	        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<StateMaster> criteriaQuery = builder.createQuery(StateMaster.class);
	        Root<StateMaster> root = criteriaQuery.from(StateMaster.class);
	        List<Predicate> predicates = new ArrayList<>();

	        // Adding stateId and stateName conditions to the query
	        if (stateId != null) {
	            predicates.add(builder.equal(root.get("stateId"), stateId));
	        }
	        if (stateName != null) {
	            predicates.add(builder.equal(root.get("stateName"), stateName));
	        }

	        // Combining predicates if there are multiple
	        if (!predicates.isEmpty()) {
	            criteriaQuery.where(predicates.toArray(new Predicate[0]));
	        }

	        // Execute query
	        List<StateMaster> stateMasterList = entityManager.createQuery(criteriaQuery).getResultList();

	        // Map StateMaster objects to StateDto objects
	        List<StateDto> stateDtoList = stateMasterList.stream().map(mapper::mapStateEntityToStateDto)
	                .collect(Collectors.toList());
	        // Create a new Excel workbook
	        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
	            XSSFSheet sheet = workbook.createSheet("State Data");
	            Row headerRow = sheet.createRow(0);
	            String[] headers = { "State ID", "State Name", "Is Active", "Is Deleted", "Created By", "Created Date",
	                    "Modified By", "Modified Date" };
	            XSSFCellStyle headerCellStyle = workbook.createCellStyle();
	            XSSFFont headerFont = workbook.createFont();
	            headerFont.setBold(true);
	            headerCellStyle.setFont(headerFont);
	            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
	            // Populate header row with column names
	            for (int i = 0; i < headers.length; i++) {
	                Cell cell = headerRow.createCell(i);
	                cell.setCellValue(headers[i]);
	                cell.setCellStyle(headerCellStyle);
	            }
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            // Populate data rows
	            int rowNum = 1;
	            for (StateDto stateDto : stateDtoList) {
	                Row row = sheet.createRow(rowNum++);
	                row.createCell(0).setCellValue(stateDto.getStateId());
	                row.createCell(1).setCellValue(stateDto.getStateName());
	                row.createCell(2).setCellValue(stateDto.getIsActive());
	                row.createCell(3).setCellValue(stateDto.getIsDeleted());
	                // Extract AuditEntity details
	                AuditEntity auditEntity = stateDto.getAuditEntity();
	                if (auditEntity != null) {
	                    // Populate createdBy
	                    row.createCell(4).setCellValue(auditEntity.getCreatedBy());
	                    // Populate createdDate
	                    row.createCell(5)
	                            .setCellValue(auditEntity.getCreatedDate() != null
	                                    ? auditEntity.getCreatedDate().format(formatter)
	                                    : "");
	                    Integer modifiedBy = auditEntity.getModifiedBy();
	                    if (modifiedBy != null) {
	                        row.createCell(6).setCellValue(modifiedBy.intValue());
	                    } else {
	                        row.createCell(6).setCellValue("");
	                    }
	                    row.createCell(7)
	                            .setCellValue(auditEntity.getModifiedDate() != null
	                                    ? auditEntity.getModifiedDate().format(formatter)
	                                    : "");
	                } else {
	                    for (int i = 4; i <= 7; i++) {
	                        row.createCell(i).setCellValue("");
	                    }
	                }
	            }
	            // Save the workbook to the specified file path
	            File file = new File(filePath);
	            try (FileOutputStream outputStream = new FileOutputStream(file)) {
	                workbook.write(outputStream);
	            }
	        }
	        log.info("Excel generated and saved successfully at: {}", filePath);
	        return new StateApiResponse(null, null, null, HttpStatus.OK, "Excel generated and saved successfully",
	                true);
	    } catch (IOException e) {
	        log.error("Error occurred while generating and saving Excel at: {}", filePath, e);
	        return new StateApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,
	                "An error occurred while generating and saving Excel", false);
	    }
	}


	@Override
	public StateApiResponse generateAndSavePDF(List<StateDto> stateDtoList) {
		// Specify the file path where the PDF file will be saved
		String filePath1 = "M:\\SIPL Project\\TRACKER_PROJECT\\states.pdf";
		// Create a document with landscape orientation
		Document document = new Document(PageSize.A4.rotate());

		try {
			// Create a PdfWriter instance to write to the specified file path
			PdfWriter.getInstance(document, new FileOutputStream(filePath1));
			// Open the document for writing
			document.open();

			// Create a table with 8 columns
			PdfPTable table = new PdfPTable(8);
			table.setWidthPercentage(100); // Set table width to 100% of the page

			// Add table headers
			String[] headers = { "State ID", "State Name", "Is Active", "Is Deleted", "Created By", "Created Date",
					"Modified By", "Modified Date" };
			for (String header : headers) {
				PdfPCell headerCell = new PdfPCell();
				headerCell.setPhrase(new Phrase(header));
				table.addCell(headerCell);
			}

			// Add table rows with data
			for (StateDto stateDto : stateDtoList) {
				table.addCell(String.valueOf(stateDto.getStateId()));
				table.addCell(stateDto.getStateName());
				table.addCell(String.valueOf(stateDto.getIsActive()));
				table.addCell(String.valueOf(stateDto.getIsDeleted()));

				// Add more fields as needed

				// Create PdfPCell objects for AuditEntity fields
				PdfPCell createdByCell = new PdfPCell();
				PdfPCell createdDateCell = new PdfPCell();
				PdfPCell modifiedByCell = new PdfPCell();
				PdfPCell modifiedDateCell = new PdfPCell();

				// Set content for each cell
				if (stateDto.getAuditEntity() != null) {
					createdByCell.addElement(new Phrase(stateDto.getAuditEntity().getCreatedBy()));
					createdDateCell.addElement(new Phrase(stateDto.getAuditEntity().getCreatedDate() != null
							? stateDto.getAuditEntity().getCreatedDate().toString()
							: ""));
					modifiedByCell.addElement(new Phrase(stateDto.getAuditEntity().getModifiedBy()));
					modifiedDateCell.addElement(new Phrase(stateDto.getAuditEntity().getModifiedDate() != null
							? stateDto.getAuditEntity().getModifiedDate().toString()
							: ""));
				}

				// Add cells to the table
				table.addCell(createdByCell);
				table.addCell(createdDateCell);
				table.addCell(modifiedByCell);
				table.addCell(modifiedDateCell);
			}

			// Add the table to the document
			document.add(table);

			// Close the document
			document.close();
			// Log the success of PDF generation and saving
			log.info("PDF generated and saved successfully at: {}", filePath1);
			// Return a success response
			return new StateApiResponse(null, stateDtoList, null, null, "PDF generated and saved successfully", true);
		} catch (Exception e) {
			// Log any error that occurs during PDF generation and saving
			log.error("An error occurred while generating and saving PDF at: {}", filePath1, e);
			// Return an error response
			return new StateApiResponse(null, stateDtoList, null, null, "Failed to generate and save PDF", false);
		}
	}
}
