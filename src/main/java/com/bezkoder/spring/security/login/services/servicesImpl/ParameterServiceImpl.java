
package com.bezkoder.spring.security.login.services.servicesImpl;

        import com.bezkoder.spring.security.login.models.Parameter;
        import com.bezkoder.spring.security.login.models.Project;
        import com.bezkoder.spring.security.login.repository.ParameterRepository;
        import com.bezkoder.spring.security.login.services.ParameterService;
        import com.bezkoder.spring.security.login.services.ParameterService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Objects;

@Service

// Class
class ParameterServiceImpl implements ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;

    // Save operation
    @Override
    public Parameter saveParameter(Parameter parameter)
    {
        return parameterRepository.save(parameter);
    }

    // Read operation
    @Override public List<Parameter> fetchParameterList()
    {
        return (List<Parameter>)
                parameterRepository.findAll();
    }

    // Update operation
    @Override
    public Parameter updateParameter(Parameter parameter, Long ParameterId)
    {
        Parameter depDB
                = parameterRepository.findById(ParameterId)
                .get();

        if (Objects.nonNull(parameter.getName())
                && !"".equalsIgnoreCase(
                parameter.getName())) {
            depDB.setName(
                    parameter.getName());
        }


        return parameterRepository.save(depDB);
    }



    // Delete operation
    @Override
    public void deleteParameterById(Long ParameterId)
    {
        parameterRepository.deleteById(ParameterId);
    }

    @Override
    public List<Parameter> fetchParameterListByName(String ParameterName) {
        return (List<Parameter>)
                parameterRepository.findByNameContaining(ParameterName).get();
    }

    @Override
    public Parameter fetchParameterListById(Long id) {
        return (Parameter)
                parameterRepository.findById(id).get();
    }
}
