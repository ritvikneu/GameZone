# GameZone

This full-stack web app uses Java, Spring, Hibernate, MySQL, and JavaScript to facilitate slot booking for playing games with friends, enrolling in tournaments, adding scores, and checking leaderboards. The app provides real-time notifications and an easy-to-use interface. Hibernate simplifies data access and MySQL stores user data. 

In 'Web Development and Tools,' I gained expertise in advanced server-side technologies and tools vital for the swift design and engineering of comprehensive web-based enterprise applications. This course enriched my understanding of the complete life cycle of web applications, with a focus on MVC web development frameworks for data-intensive and multitier applications. Moreover, I delved into designing rich internet applications (RIA) using AJAX and grasped service-oriented architecture (SOA) using REST

RUN AND DEPLOY:
1. /GameZone is the default path
2. Open as a Spring Boot application
3. Change the port in application.properties
4. Set up  database in HibernateUtil.java - dialect, username and password
5. Run as Spring Boot Application

1. /admin/getuniv - Get all Universities
2. /admin/adduniv.htm - Add Universities to the application
3. admin/addgames.htm - Add games under universities
4. /admin/deleteGame.htm - Delete a game
5. /admin/updateSlots.htm - Update the slots available for the game
6. /admin/getLeaderBoard.htm - Get LeaderBoard
7. /admin/getAllBooking.htm - Get All Bookings
8. /booking/cancelBooking.htm - Cancel Bookings
9. /booking/modifyBooking.htm - Modify Booking
10. /booking/joinBooking.htm - Join a Zone
11. /booking/showZoners.htm - Show people in the booking
12. /gamer/register.htm - Register as Gamer
13. /gamer/getAllGames.htm - Get all Games for the gamer
14. /gamer/gamer-booking-list.htm - Get gamer booking list
15. /gamer/addScores.htm - Add scores to the played games
