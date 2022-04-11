package org.molodyko.hibernate_ex.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.molodyko.hibernate_ex.converter.LocalDateConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "holidays", schema = "public")
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dateStart;
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dateEnd;
    private Integer userId;
    private Integer holidayTypeId;
}