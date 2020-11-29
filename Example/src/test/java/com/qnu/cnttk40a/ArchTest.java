package com.qnu.cnttk40a;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.qnu.cnttk40a");

        noClasses()
            .that()
                .resideInAnyPackage("com.qnu.cnttk40a.service..")
            .or()
                .resideInAnyPackage("com.qnu.cnttk40a.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.qnu.cnttk40a.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
