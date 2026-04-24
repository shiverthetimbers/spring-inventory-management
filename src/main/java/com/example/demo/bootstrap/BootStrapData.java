package com.example.demo.bootstrap;

import com.example.demo.domain.*;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository, RecipeRepository recipeRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Part starterPart = null;

        if (partRepository.count() == 0) {

            List<InhousePart> starterParts = Arrays.asList(
                    new InhousePart("CPU, 4 Cores", 100.00, 10, 0, 50),
                    new InhousePart("RAM, 8GB", 50.00, 10, 0, 50),
                    new InhousePart("RAM, 16GB", 75.00, 10, 0, 50),
                    new InhousePart("SSD, 512GB", 60.00, 10, 0, 50),
                    new InhousePart("HDD, 2TB", 80.00, 10, 0, 50)
            );

            starterPart = starterParts.get(0);
            partRepository.saveAll(starterParts);
        }

        if (productRepository.count() == 0) {

            List<Product> starterProducts = Arrays.asList(
                    new Product("The Typewriter", 200.00, 4),
                    new Product("The Browser", 300.00, 4),
                    new Product("The Workhorse", 400.00, 2),
                    new Product("The Gamer", 500.00, 2),
                    new Product("The Continuum Transfunctioner", 800.00, 1)
            );

            for (Product each : starterProducts) {

                RecipeLine starterLine = new RecipeLine(starterPart, 1);
                Recipe starterRecipe = new Recipe();

                each.setRecipe(starterRecipe);
                starterRecipe.addRecipeLine(starterLine);

            }

            productRepository.saveAll(starterProducts);
        }

        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
