package hu.ait.plantordieapp.data

data class PlantResult(val varieties: String?, val sub_species: String?, val scientific_name: String?, val order: Order?, val native_status: String?, val main_species: Main_species?, val images: List<Images1280526236>?, val id: String?, val hybrids: String?, val genus: Genus?, val forms: String?, val family_common_name: String?, val family: Family?, val duration: String?, val division: Division?, val cultivars: String?, val common_name: String?, val class_name: Class?)

data class Class(val slug: String?, val name: String?, val link: String?, val id: String?)

data class Division(val slug: String?, val name: String?, val link: String?, val id: String?)

data class Family(val slug: String?, val name: String?, val link: String?, val id: String?, val common_name: String?)

data class Flower(val conspicuous: String?, val color: String?)

data class Foliage(val texture: String?, val porosity_winter: String?, val porosity_summer: String?, val color: String?)

data class Fruit_or_seed(val seed_persistence: String?, val seed_period_end: String?, val seed_period_begin: String?, val seed_abundance: String?, val conspicuous: String?, val color: String?)

data class Genus(val slug: String?, val name: String?, val link: String?, val id: String?)

data class Growth(val temperature_minimum: Temperature_minimum?, val shade_tolerance: String?, val salinity_tolerance: String?, val root_depth_minimum: Root_depth_minimum?, val resprout_ability: String?, val precipitation_minimum: Precipitation_minimum?, val precipitation_maximum: Precipitation_maximum?, val planting_density_minimum: Planting_density_minimum?, val planting_density_maximum: Planting_density_maximum?, val ph_minimum: String?, val ph_maximum: String?, val moisture_use: String?, val hedge_tolerance: String?, val frost_free_days_minimum: String?, val fire_tolerance: String?, val fertility_requirement: String?, val drought_tolerance: String?, val cold_stratification_required: String?, val caco_3_tolerance: String?, val anaerobic_tolerance: String?)

data class Images1280526236(val url: String?)

data class Images1675087599(val url: String?)

data class Main_species(val year: String?, val type: String?, val synonym: String?, val status: String?, val specifications: Specifications?, val sources: List<Sources2101225246>?, val soils_adaptation: Soils_adaptation?, val slug: String?, val seed: Seed?, val scientific_name: String?, val propagation: Propagation?, val products: Products?, val native_status: String?, val main_species_id: String?, val is_main_species: String?, val images: List<Images1675087599>?, val id: String?, val growth: Growth?, val fruit_or_seed: Fruit_or_seed?, val foliage: Foliage?, val flower: Flower?, val family_common_name: String?, val duration: String?, val complete_data: String?, val common_name: String?, val bibliography: String?, val author: String?)

data class Mature_height(val ft: String?, val cm: String?)

data class Max_height_at_base_age(val ft: String?, val cm: String?)

data class Order(val slug: String?, val name: String?, val link: String?, val id: String?)

data class Planting_density_maximum(val sqm: String?, val acre: String?)

data class Planting_density_minimum(val sqm: String?, val acre: String?)

data class Precipitation_maximum(val inches: String?, val cm: String?)

data class Precipitation_minimum(val inches: String?, val cm: String?)

data class Products(val veneer: String?, val pulpwood: String?, val protein_potential: String?, val post: String?, val palatable_human: String?, val palatable_graze_animal: String?, val palatable_browse_animal: String?, val nursery_stock: String?, val naval_store: String?, val lumber: String?, val fuelwood: String?, val fodder: String?, val christmas_tree: String?, val berry_nut_seed: String?)

data class Propagation(val tubers: String?, val sprigs: String?, val sod: String?, val seed: String?, val cuttings: String?, val corms: String?, val container: String?, val bulbs: String?, val bare_root: String?)

data class Root_depth_minimum(val inches: String?, val cm: String?)

data class Seed(val vegetative_spread_rate: String?, val small_grain: String?, val seeds_per_pound: String?, val seedling_vigor: String?, val seed_spread_rate: String?, val commercial_availability: String?, val bloom_period: String?)

data class Soils_adaptation(val medium: String?, val fine: String?, val coarse: String?)

data class Sources2101225246(val species_id: String?, val source_url: String?, val name: String?, val last_update: String?)

data class Specifications(val toxicity: String?, val shape_and_orientation: String?, val regrowth_rate: String?, val nitrogen_fixation: String?, val max_height_at_base_age: Max_height_at_base_age?, val mature_height: Mature_height?, val low_growing_grass: String?, val lifespan: String?, val leaf_retention: String?, val known_allelopath: String?, val growth_rate: String?, val growth_period: String?, val growth_habit: String?, val growth_form: String?, val fire_resistance: String?, val fall_conspicuous: String?, val coppice_potential: String?, val c_n_ratio: String?, val bloat: String?)

data class Temperature_minimum(val deg_f: String?, val deg_c: String?)

