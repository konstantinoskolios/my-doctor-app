package com.example.mydoctorapp.specifications;

import com.example.mydoctorapp.entities.Citizen;
import com.example.mydoctorapp.entities.Citizen_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CitizenSpecification {

    private static Specification<Citizen> findByField(String field, String fieldValue) {
        return ((root, query, criteriaBuilder) ->
                StringUtils.isBlank(field) ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get(fieldValue), field));
    }

    public static Specification<Citizen> constructCitizenSpecification(String input) {

        if (StringUtils.isBlank(input) || !StringUtils.isNumeric(input)) {
            return null;
        }

        switch (StringUtils.length(input)) {
            case 9:
                return findByField(input, Citizen_.TAX_NUMBER);
            case 10:
                return findByField(input, Citizen_.PHONE_NUMBER);
            case 11:
                return findByField(input, Citizen_.SOCIAL_SECURITY_NUMBER);
            default:
                return null;
        }
    }
}
