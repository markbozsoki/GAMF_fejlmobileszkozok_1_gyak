# GAMF_fejlmobileszkozok_1_gyak

## App description

A personal money management and budgeting app, capable of processing user-provided data about monthly income and costs.
The app can also help visualize the current and predicted state of the user's assets and investments.

## Core functionalities

- Registering and visualizing monthly income and spending by category
   - The user does not need to create a profile
- Registering the current value of already owned assets, investments, and debts
- Visualization of the current state of the users wealth
- Based on the already acquired assets, investments, debts, and monthly income, the app:
   1. Estimates the growth or decline of wealth
   2. Calculates a personal emergency fund and the time required to build it up
- The user can apply expected additional income or spending to make the future wealth estimation more accurate

---

### Storyboard

```in progress```

---

### Contributors

- [**Óré Szabina Vivien**](https://github.com/MoSzabina)
- [**Kovács Norbert**](https://github.com/norbertkovacsgit)
- [**Bozsóki Márk**](https://github.com/markbozsoki)

---

### Development and technical details

<details><summary><h3> Szabina Notes </h3></summary>

- added MPAndroidChart library (JitPack repo + libs.versions.toml + app module) using (https://github.com/PhilJay/MPAndroidChart)

</details>

---

<details><summary><h3> Norbi Notes </h3></summary>

- Creating Home & Test fragments
- Adding home.png as the picture of the Home button
- Making the layouts for each component:
   - fragment_home
   - fragment_test
   - main_menu
   - nav_graph
   - Updating the activity_main for the Toolbar implementation
- Extending strings.xml for fragment name aliases
- Updating MainActivity with Toolbar and Navigation functions

</details>

---

<details><summary><h3> Márk Notes </h3></summary>

- added empty project with readme and an automated GH action to build, test and lint the project
- defined repository filesystem

</details>

---
### Dependencies

- MPAndroidChart – chart rendering library (https://javadoc.jitpack.io/com/github/PhilJay/MPAndroidChart/v3.1.0/javadoc/)
- Navigation and UI libraries
- Fragment

<p align="right"><sub>2025/26/1</sub></p>
