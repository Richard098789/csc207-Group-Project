# Music Artist and Event Management System

## Overview

The **Music Artist and Event Management System** is a Java-based software project designed to manage music artist information, events, and user interactions. It incorporates clean architecture, follows SOLID design principles, and integrates real-time data from external APIs. This project provides a robust framework for managing artist-related data and event search while ensuring user-centric functionality.

## Authors and Contributors
This project was developed by the **CSC207 Software Design Team**:
- **Abhigyan Dey**: Project Lead, Core Architecture Design
- **Richard**: API Integration, GUI Development
- **Nick**: Use Case Implementation
- **Chris**: Unit Testing and Documentation


---

## Table of Contents

1. [Summary of the Project’s Purpose](#summary-of-the-projects-purpose)
2. [Features](#features)
2. [Installation](#installation)
3. [Usage](#usage)
4. [Architecture](#architecture)
5. [License](#license)
6. [Feedback and Contributions](#feedback-and-contributions)

---
## Summary of the Project’s Purpose

### What This Project Does
The **Music Artist and Event Explorer** application provides users with an interactive platform to:
- **Create an account** and securely log in.
- **Search for artists** and view their top songs, detailed information, and public ratings.
- **Write and view comments** and rate artists, contributing to an average score that dynamically updates.
- **Search for events**, allowing users to explore music-related happenings.

### Why the Project Was Made
This application was developed to:
- **Simplify music exploration** by combining artist and event search capabilities in one app.
- **Provide a platform for user interaction**, enabling music lovers to share opinions, rate artists, and discover community insights.
- **Apply practical software development concepts**, including clean architecture and user-centric design, as part of the **CSC207 Software Design course.**

### What Problem This Project Solves
The project addresses the need for:
- A **centralized platform** for discovering and interacting with music artists and events.
- An **interactive community-driven system** where users can provide and access real-time feedback about artists.
- **Streamlined event exploration**, making it easy for users to connect with music-related events in their area.

### Usefulness for Users
This application is beneficial for:
- **Music enthusiasts** seeking detailed artist insights and community opinions.
- **Casual users** looking for a quick way to explore popular artists and events.
- **Developers and learners** exploring the integration of clean architecture, dynamic data updates, and user interface design principles in a functional app.


## Features

### User Account Management
- **Create Account**: Users can easily create a new account with secure credentials.
- **Login System**: Existing users can log in to access personalized features and interact with the app.

### Artist Search and Exploration
- **Artist Listings**: Search for artists by name and explore their information.
- **Dynamic Song Information**: View a curated list of the artist’s top songs, with details including song titles and length.
- **Rating System**: Users can rate artists on a scale and leave personalized comments.
- **Community Feedback**: View other users' comments and ratings in a dedicated section.
- **Average Rating Updates**: The average score dynamically updates with every new user rating.
- **Persistent Storage**: Comments and ratings entered by users are securely stored in a Firebase database and are displayed on the artist detail page.

### Event Search
- **Event Listings**: Search for music-related events .
- **Event Details**: View comprehensive event information, including venue and date.

### Interactive User Interface
- **Responsive Design**: Intuitive navigation through artist and event search functionalities.
- **Engaging Feedback System**: Write comments and rate artists while seamlessly viewing aggregated community responses.
- **Real-Time Updates**: Ratings and comments update dynamically, providing a live experience.

### Clean Architecture Implementation
- **Modular Design**: The application is structured with clearly defined modules for ease of maintenance and scalability.
- **Separation of Concerns**: Each feature is implemented as an independent module, ensuring flexibility and clarity in the codebase.

### Additional Features
- **Secure Data Management**: User accounts and interactions are managed securely using modern data storage techniques like Firebase.
- **Error Handling**: The application gracefully handles invalid inputs and provides informative feedback to the user.
- **Cross-Platform Compatibility**: Designed to function on various operating systems, ensuring accessibility for all users.

---

## Installation Instructions

Follow the steps below to install and set up the project on your local machine.

### Prerequisites

Before you begin, ensure you have the following installed on your system:
1. **Java Development Kit (JDK)**: Version 17 or higher is required.
- Download from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/).
2. **Maven**: For dependency management and project building.
- Download from [Maven's Official Website](https://maven.apache.org/download.cgi).
3. **Git**: For cloning the repository.
- Download from [Git's Official Website](https://git-scm.com/downloads).
4. **Firebase Admin SDK**: For managing database interactions. Ensure you have access to your Firebase project credentials file (`.json`).
- Follow the [Firebase Admin Setup Guide](https://firebase.google.com/docs/admin/setup) to generate the credentials file.

### Steps to Install

1. **Clone the Repository**
```bash
git clone https://github.com/Richard098789/csc207-Group-Project.git
cd <csc207-Group-Project>
```

2. **Step 6: Run the Application**

