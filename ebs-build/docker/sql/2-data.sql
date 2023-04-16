INSERT INTO public.article (id, body, headline, image_path, created_on) VALUES (1, 'Gas leak body...', 'Gas Leak in Whitchurch', 'https://upload.wikimedia.org/wikipedia/commons/f/f0/Purplesmoke.jpg', current_time);
INSERT INTO public.article (id, body, headline, image_path, created_on) VALUES (2, 'Fire body...', 'Fire in Grangetown', 'https://i2-prod.mirror.co.uk/incoming/article26571255.ece/ALTERNATES/s1200c/2_TDP_CHP_280322Nottinghamshire-Fire-and-Rescue-Service_1569.jpg', current_time);
INSERT INTO public.article (id, body, headline, image_path, created_on) VALUES (3, 'Toxic spill body...', 'Toxic spill near Cardiff Bay ', 'https://www.lieffcabraser.com/wp-content/uploads/oil_spill-blog.jpg', current_time);

INSERT INTO public.graph (id, title, type, title_label) VALUES (1, 'Crime stats', 'crime', 'Case of Crime (100s)');
INSERT INTO public.graph (id, title, type, title_label) VALUES (2, 'Emergency Warning Status', 'warning', 'Level Severity (1-5) ');
INSERT INTO public.graph (id, title, type, title_label) VALUES (3, 'COVID-19 Cases In Cardiff', 'covid', 'Cases of Covid (100s)');

INSERT INTO public.data (id, data) VALUES (1, 1);
INSERT INTO public.data (id, data) VALUES (1, 2);
INSERT INTO public.data (id, data) VALUES (1, 4);
INSERT INTO public.data (id, data) VALUES (1, 10);
INSERT INTO public.data (id, data) VALUES (1, 5);
INSERT INTO public.data (id, data) VALUES (1, 3);
INSERT INTO public.data (id, data) VALUES (2, 5);
INSERT INTO public.data (id, data) VALUES (2, 4);
INSERT INTO public.data (id, data) VALUES (2, 2);
INSERT INTO public.data (id, data) VALUES (2, 3);
INSERT INTO public.data (id, data) VALUES (2, 5);
INSERT INTO public.data (id, data) VALUES (2, 1);
INSERT INTO public.data (id, data) VALUES (3, 350);
INSERT INTO public.data (id, data) VALUES (3, 300);
INSERT INTO public.data (id, data) VALUES (3, 250);
INSERT INTO public.data (id, data) VALUES (3, 270);
INSERT INTO public.data (id, data) VALUES (3, 150);
INSERT INTO public.data (id, data) VALUES (3, 200);

INSERT INTO public.labels (id, labels) VALUES (1, 'Jan');
INSERT INTO public.labels (id, labels) VALUES (1, 'Feb');
INSERT INTO public.labels (id, labels) VALUES (1, 'Mar');
INSERT INTO public.labels (id, labels) VALUES (1, 'Apr');
INSERT INTO public.labels (id, labels) VALUES (1, 'May');
INSERT INTO public.labels (id, labels) VALUES (1, 'Jun');
INSERT INTO public.labels (id, labels) VALUES (2, 'Terrorism Threat');
INSERT INTO public.labels (id, labels) VALUES (2, 'Environmental Hazard');
INSERT INTO public.labels (id, labels) VALUES (2, 'Public Health Emergency');
INSERT INTO public.labels (id, labels) VALUES (2, 'Civil Emergency');
INSERT INTO public.labels (id, labels) VALUES (2, 'Transportation Disruption');
INSERT INTO public.labels (id, labels) VALUES (2, 'Natural Disasters');
INSERT INTO public.labels (id, labels) VALUES (3, 'Jan');
INSERT INTO public.labels (id, labels) VALUES (3, 'Feb');
INSERT INTO public.labels (id, labels) VALUES (3, 'Mar');
INSERT INTO public.labels (id, labels) VALUES (3, 'Apr');
INSERT INTO public.labels (id, labels) VALUES (3, 'May');
INSERT INTO public.labels (id, labels) VALUES (3, 'Jun');

INSERT INTO public.heatmap (id, latitude, longitude, weight) VALUES (1, 37.7749, -122.4194, 5);
INSERT INTO public.heatmap (id, latitude, longitude, weight) VALUES (2, 51.481583, -3.17909, 10);
INSERT INTO public.heatmap (id, latitude, longitude, weight) VALUES (3, 54.607868, -5.926437, 5);
INSERT INTO public.heatmap (id, latitude, longitude, weight) VALUES (4, 48.856613, 2.352222, 5);

INSERT INTO public.resource (id, advice_panel, advice_panel_heading, description_heading, description_panel, list_items_heading, title) VALUES (1, e'When the terrorism threat level is elevated, high, or severe, you should be prepared for the possibility of a terrorist attack. This includes being aware of your surroundings, reporting any suspicious activity to law enforcement, and having an emergency plan in place.

If an attack occurs, you should follow the instructions of law enforcement and emergency personnel. If you are in a public place, you should try to find a safe location and stay there until it is safe to leave.', 'What should you do?', 'What is the terrorism threat level system?', e'The terrorism threat level system is a way for the government to communicate the likelihood of a terrorist attack in a particular area. The system uses a color-coded scale with five levels, ranging from low to severe.

The terrorism threat level is determined by a variety of factors, including intelligence information, the overall global threat picture, and specific threat information regarding the United States.', 'What do the different threat levels mean?', 'Terrorism');
INSERT INTO public.resource (id, advice_panel, advice_panel_heading, description_heading, description_panel, list_items_heading, title) VALUES (2, e'If you are facing a transportation disruption, it is important to stay informed about any updates or changes to transportation services. You can check local news and transportation authority websites for information about delays, cancellations, or alternative transportation options.

You can also take steps to prepare for potential transportation disruptions, such as planning alternative routes or modes of transportation, allowing extra travel time, and keeping emergency supplies on hand in case of unexpected delays or disruptions.', 'What should you do?', 'What are transportation disruptions?', 'Transportation disruptions occur when there is an unexpected interruption or delay in transportation services. These disruptions can be caused by a variety of factors, such as inclement weather, accidents, maintenance issues, or other unexpected events.', 'What are the different types of transportation disruptions?', 'Transportation');
INSERT INTO public.resource (id, advice_panel, advice_panel_heading, description_heading, description_panel, list_items_heading, title) VALUES (3, e'When facing a public health emergency, it is important to follow the instructions of public health officials and emergency personnel. This may include following quarantine or isolation orders, getting vaccinated, or taking other preventive measures to protect yourself and your community.

You can also take steps to reduce your risk of exposure to infectious diseases, such as washing your hands frequently and avoiding close contact with sick individuals. If you experience symptoms of illness or injury, seek medical attention promptly.', 'What should you do?', 'What are public health emergencies?', e'Public health emergencies are events or situations that pose a significant threat to the health of a community or population. These emergencies can include outbreaks of infectious diseases, natural disasters, chemical or radiation exposure, and other health hazards.

', 'What are the different types of public health emergencies?', 'Public Health');
INSERT INTO public.resource (id, advice_panel, advice_panel_heading, description_heading, description_panel, list_items_heading, title) VALUES (4, e'If you are facing a civil emergency, it is important to follow the instructions of law enforcement or emergency personnel. This may include evacuating the area, sheltering in place, or taking other protective measures to ensure your safety.

You can also take steps to prepare for potential civil emergencies, such as developing an emergency plan for your family, staying informed about local emergency alerts and warnings, and keeping emergency supplies on hand.

', 'What should you do?', 'What are civil emergencies?', e'Civil emergencies are situations that pose a threat to public safety and may require a response from law enforcement or emergency services. These emergencies can include natural disasters, public disturbances, hazardous material spills, and other incidents that can disrupt normal community functions.

', 'What are the different types of civil emergencies?', 'Civil Emergencies');
INSERT INTO public.resource (id, advice_panel, advice_panel_heading, description_heading, description_panel, list_items_heading, title) VALUES (5, e'When facing environmental hazards, it is important to be prepared and take appropriate actions to protect yourself and your community. This includes staying informed about weather conditions and potential hazards, creating emergency plans, and following instructions from emergency personnel.

If you encounter hazardous waste, pollution, or other environmental hazards, report it to the appropriate authorities. Take steps to reduce your own environmental impact by conserving resources, reducing waste, and choosing environmentally friendly products and practices.', 'What should you do?', 'What are environmental hazards?', e'Environmental hazards are events or situations in the natural world that pose a threat to human health or the environment. These hazards can include natural disasters, such as hurricanes and earthquakes, as well as human-made hazards, such as pollution and hazardous waste.

', 'What are the different types of environmental hazards?', 'Environmental Hazards');
INSERT INTO public.resource (id, advice_panel, advice_panel_heading, description_heading, description_panel, list_items_heading, title) VALUES (6, e'If you are facing a natural disaster, it is important to stay informed about the latest updates and recommendations from local authorities. You can check local news and emergency management websites for information about evacuation orders, emergency shelters, and other resources.
You can also take steps to prepare for potential natural disasters, such as creating an emergency supply kit, developing an evacuation plan, and staying informed about potential hazards in your area.', 'What should you do', 'What are natural disasters', 'Natural disasters are catastrophic events caused by natural phenomena such as earthquakes, hurricanes, floods, wildfires, tornadoes, and volcanic eruptions. These events can cause significant damage to infrastructure and property, as well as loss of life and injuries.', 'What are the different types of natural disasters?', 'Natural Disasters');


INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (1, 'A terrorist attack is unlikely.', 'Low:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (1, 'A terrorist attack is possible, but not likely.', 'Guarded:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (1, 'A terrorist attack is credible and likely.', 'Elevated:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (1, 'A terrorist attack is highly likely.', 'High:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (1, 'A terrorist attack is imminent or has occurred.', 'Severe:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (2, 'These can include road closures or flight cancellations due to severe weather conditions, such as snowstorms, hurricanes, or heavy rain.', 'Weather-related:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (2, 'Accidents on highways, railways, or airports can cause significant disruptions to transportation services.', 'Accidents:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (2, 'Transportation services may be disrupted due to scheduled maintenance or repair work on vehicles, tracks, or other infrastructure.', 'Maintenance issues:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (2, 'Transportation services can also be disrupted due to unexpected events such as strikes, protests, or security incidents.', 'Other unexpected events:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (3, 'These can include outbreaks of diseases such as influenza, Ebola, and COVID-19.', 'Infectious disease outbreaks:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (3, 'These include hurricanes, tornadoes, earthquakes, floods, wildfires, and other severe weather events that can cause injuries, disease, and other health problems.', 'Natural disasters:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (3, 'Exposure to toxic chemicals or radiation can cause a wide range of health problems, from skin irritation to cancer.', 'Chemical or radiation exposure:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (3, 'These can include foodborne illnesses, workplace accidents, and other situations that can lead to illness or injury.', 'Other health hazards:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (4, 'These can include riots, protests, and other events that may threaten public safety and require intervention by law enforcement or other emergency personnel.', 'Public disturbances:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (4, 'Accidental or intentional release of hazardous materials, such as chemicals or radiation, can pose a serious threat to public health and safety.', 'Hazardous material spills:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (4, 'These can include infrastructure failures, such as power outages or transportation disruptions, that can impact the daily lives of individuals and communities.', 'Other incidents:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (5, 'These include hurricanes, tornadoes, earthquakes, floods, wildfires, and other severe weather events.', 'Natural disasters:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (5, 'This includes air pollution, water pollution, and soil contamination from sources such as factories, transportation, and agriculture.', 'Pollution:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (5, 'This includes chemicals, radioactive materials, and other toxic substances that can cause harm to humans and the environment.', 'Hazardous waste:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (5, 'This includes the long-term changes in weather patterns and other environmental conditions caused by human activities, such as the burning of fossil fuels.', 'Climate change:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (6, 'Earthquakes are caused by the shifting of tectonic plates beneath the Earth''s surface and can result in significant structural damage and loss of life.', 'Earthquakes:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (6, 'Hurricanes and tropical storms are powerful storms that can bring high winds, heavy rain, and flooding.', 'Hurricanes and tropical storms:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (6, 'Floods can be caused by heavy rain, storm surge, or the failure of infrastructure such as levees or dams.', 'Floods:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (6, 'Wildfires can be caused by lightning strikes or human activity and can cause significant damage to forests, homes, and other structures.', 'Wildfires:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (6, 'Tornadoes are violent windstorms that can cause significant damage to buildings and infrastructure.', 'Tornadoes:');
INSERT INTO public.resource_list_items (resource_id, list_items, list_items_key) VALUES (6, 'Volcanic eruptions can cause significant damage to infrastructure and property, as well as pose health risks due to the release of ash, gas, and lava.', 'Volcanic eruptions:');
