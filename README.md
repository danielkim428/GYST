[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)
# 3500 PA05 Project Repo

[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)

# GUI
![img1.png](screenshots%2Fimg1.png)
![img2.png](screenshots%2Fimg2.png)
![img3.png](screenshots%2Fimg3.png)
![img4.png](screenshots%2Fimg4.png)
![img5.png](screenshots%2Fimg5.png)
![img6.png](screenshots%2Fimg6.png)

# Pitch
- Introducing GYST: Get Your Sh*t Together, the ultimate bullet journaling app 
that seamlessly combines event and task management, customizable themes,
and intuitive interface. Don't want people to see your information?
Lucky for you, GYST provides Captcha verification to ensure your identity isn't stolen.
We don't know why anyone would want to steal your information, given that you
downloaded this application to Get Your Sh!t Together, implying that you currently
DON'T have your sh!t together! Effortlessly add, track, 
and personalize your journal with GYST. Simplify your life with GYST,
the ultimate bullet journaling app.

# SOLID
## S
- Each class has a single responsibility or purpose. 
- For example, classes like BujoCreater, BujoReader, and BujoWriter handle 
- specific file-related operations. Other classes like Controller,
- EventView, TaskView, and DayView seem to focus on specific aspects of the 
- application's user interface and interaction.
## O
- It's possible to extend the application's functionality by 
- adding new classes that implement existing interfaces such as Controller,
- Reader, and Writer. The application is also closed for modification in multiple
- instances. One example of this is in our interfaces. TaskView and EventsView extend ButtonView,
- BlockView has methods where a BlockView and background of some sort is created.
- The background itself cannot be modified, but can be extended by the
- TaskView and EventView
## L
- Each class that implements an interface can be easily substituted by another
- class that adheres to the common interface contracts. TaskView and EventsView
- can replace BlockView as they extend and they behave in the same way.
## I
- Each of our interfaces are specific and focused. These interfaces are made based off
- client requests. This allows for integration segregation. We do not have any overly
- generic interfaces and each one of them handles different tasks.
## D
- Our interfaces are used in many different places and for example, our Driver initializes
- the core logic of the program by creating instances of Controller and View. The view and controller
- is passed down and injected into other constructors so that ONLY one controller and
- view is used during the whole program. 
## How to extend the program
- We can easily extend the program in different ways. For example, a menu bar wouldn't be hard
to extend into our application as we followed SOLID principles. We already have the base
layout for all the functionality for a menu bar, including saving files, loading files,
creating new events, and creating new tasks. The way we would implement this is
by creating new classes that handle the MenuBar itself, as to follow SOLID principles.
These classes (such as a handler for example) would be able to make use of our
already created classes and functionality.
## Image Attributions
- https://asset.icontrolwp.com/icontrolwp.com/uploads/2016/02/google_old_captcha.png
- https://4kwallpapers.com/images/walls/thumbs/10121.png
- https://mystickermania.com/sticker-packs/among-us/among-us-jigglypuff
- https://www.deviantart.com/soyiroh/art/Uzumaki-Junji-Ito-Render-PNG-749297229
- https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pngkit.com%2Fview%2Fu2q8e6w7o0e6w7a9_death-note-and-l-image-l-png-death%2F&psig=AOvVaw09ce2-_MCtsYSd6X9-fhgJ&ust=1687516196065000&source=images&cd=vfe&ved=0CA8QjhxqFwoTCNCm4e3V1v8CFQAAAAAdAAAAABAI
- https://www.google.com/url?sa=i&url=https%3A%2F%2Fpokemon.fandom.com%2Fwiki%2FLove_Ball&psig=AOvVaw2odEuFT222L9B19NlCouaZ&ust=1687516218791000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCMDGxfjV1v8CFQAAAAAdAAAAABAD
- Marck Fontenot image is from Slack
- MarckhDietCoke is original artwork.
- https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.morvenink.com%2Ftattoo-designs%2Fp%2Fnew-jeans-bunny-3-4&psig=AOvVaw1yWpLjrZ2MmJVWZsMKpP8L&ust=1687516373640000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCICYr8PW1v8CFQAAAAAdAAAAABAD
- https://stock.adobe.com/images/cute-rabbit-head-cartoon-element/523328796?as_campaign%5B%5D=TinEye&as_campaign%5B%5D=tineye&as_content=tineye_match&clickref=1100lwRfsvae&mv=affiliate&mv2=pz&as_camptype=backlink&as_channel=affiliate&as_source=partnerize&asset_id=523328796
- https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pngmart.com%2Fimage%2F509271&psig=AOvVaw3blvL7wQcL-hoia8UGhXIr&ust=1687516508567000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCKjiu4TX1v8CFQAAAAAdAAAAABAD
- https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pokemon.com%2Fus%2Fpokedex%2Fsylveon&psig=AOvVaw2VfLiOe44JerskKNpDrCus&ust=1687516523249000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCJjd54nX1v8CFQAAAAAdAAAAABAD
- https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pngkit.com%2Fbigpic%2Fu2q8u2e6e6o0q8w7%2F&psig=AOvVaw1D5VZsFFzC3G5alYz3b6_n&ust=1687516532140000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLiF1o7X1v8CFQAAAAAdAAAAABAI


# Deployable Application
## How to run [Gyst.jar](Gyst.jar) in the root directory
### Windows
- Intellij - Run by right click, run
- File Explorer - Open with OpenJDK binary
### Mac
- Intellij - Run by right click, run
- 
