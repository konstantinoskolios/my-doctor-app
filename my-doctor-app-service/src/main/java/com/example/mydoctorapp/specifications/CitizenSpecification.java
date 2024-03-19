package com.example.mydoctorapp.specifications;

import com.example.mydoctorapp.entities.Citizen;
import com.example.mydoctorapp.entities.Citizen_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import static com.example.mydoctorapp.constants.Constants.PHONE_NUMBER_LENGTH;
import static com.example.mydoctorapp.constants.Constants.SOCIAL_SECURITY_NUMBER_LENGTH;
import static com.example.mydoctorapp.constants.Constants.TAX_NUMBER_LENGTH;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CitizenSpecification {

    private static Specification<Citizen> findByField(String field, String fieldValue) {
        return ((root, query, criteriaBuilder) ->
                StringUtils.isBlank(field) ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get(fieldValue), field));
    }

    private static Specification<Citizen> findOnlyRegisterCitizens() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isNotNull(root.get(Citizen_.registerId));
    }

    public static Specification<Citizen> constructCitizenSpecification(String input) {

        var baseSpecification = findOnlyRegisterCitizens();

        if (StringUtils.isNotBlank(input) && StringUtils.isNumeric(input)) {
            switch (StringUtils.length(input)) {
                case TAX_NUMBER_LENGTH:
                    return Specification.where(baseSpecification).and(findByField(input, Citizen_.TAX_NUMBER));
                case PHONE_NUMBER_LENGTH:
                    return Specification.where(baseSpecification).and(findByField(input, Citizen_.PHONE_NUMBER));
                case SOCIAL_SECURITY_NUMBER_LENGTH:
                    return Specification.where(baseSpecification).and(findByField(input, Citizen_.SOCIAL_SECURITY_NUMBER));
            }
        }

        return baseSpecification;
    }

}
