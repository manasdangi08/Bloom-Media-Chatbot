🤖 Bloom Media Intelligent ChatbotA professional, Java-based conversational assistant designed for Bloom Media. This chatbot streamlines client interactions by providing instant information about company services, technical stacks, and business inquiries using efficient string-matching logic.

🌟 Why is it Helpful?
1. 24/7 Availability: Provides instant responses to common client queries without human intervention.
2. Lead Generation: Directs potential clients to contact pages and service details seamlessly.
3. Brand Identity: Maintains a consistent, professional corporate tone for Bloom Media.
4. Efficiency: Reduces manual workload by handling repetitive questions about technology and office hours.
   
 🛠 Tech StackLanguage:
 1.Java 17+
 2.Framework: Spring Boot Database:
 3.MySQL (for future lead storage & logs)
 4.Build Tool: MavenAPI
 5.Style: RESTful patterns
 🚀 Key Features
 1.Smart Response System: Handles greetings, project details, and service inquiries.
 2.Live System Integration: Real-time date and time fetching using java.time API.
 3.Corporate Persona: Tailored specifically for Bloom Media’s business context.
 4.Professional UI Support: Returns HTML-formatted links for better frontend integration.
 
 📸 Project PreviewTip: 
 <img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/7fc52efb-e5f2-4550-8159-07c7993e821c" />

 ⚙️ How to Run
 1. Prerequisites
  JDK 17 or higher installed.
  Maven installed and added to your system Path.
  MySQL Server running.
2. Configuration:
 Propertiesspring.datasource.url=jdbc:mysql://localhost:3306/bloom_media_db
 spring.datasource.username=your_username
 spring.datasource.password=your_password
3. Execution:
mvn spring-boot:run

📂 Project Structure
 ChatbotController.java: Handles incoming web requests and routing.
 ChatbotService.java: Contains the core logic for message processing and professional responses.
 DemoApplication.java: The main entry point of the Spring Boot application.
 
