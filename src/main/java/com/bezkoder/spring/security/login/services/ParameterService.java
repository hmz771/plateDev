
package com.bezkoder.spring.security.login.services;

        import com.bezkoder.spring.security.login.models.Job;
        import com.bezkoder.spring.security.login.models.Parameter;
        import org.springframework.stereotype.Service;

        import java.util.List;


@Service
public interface ParameterService {

    // Save operation
    Parameter saveParameter(Parameter Parameter);

    // Read operation
    List<Parameter> fetchParameterList();

    // Update operation
    Parameter updateParameter(Parameter Parameter,
                  Long JobId);

    // Delete operation
    void deleteParameterById(Long ParameterId);


    List<Parameter> fetchParameterListByName(String name);

    Parameter fetchParameterListById(Long parameterId);
}
