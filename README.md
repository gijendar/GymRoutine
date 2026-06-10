<div align="center">

# 🏋️ GymRoutine

### Plan. Train. Progress.

**A beautifully designed, offline-first workout routine builder and training logger for Android.**

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)](https://www.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Material 3](https://img.shields.io/badge/Design-Material%203-757575?logo=material-design&logoColor=white)](https://m3.material.io)
[![Room](https://img.shields.io/badge/Database-Room-orange)](https://developer.android.com/training/data-storage/room)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-26%20(Android%208.0)-green)](https://developer.android.com/tools/releases/platforms)
[![Version](https://img.shields.io/badge/Version-1.0.0-blue)](https://github.com)
[![License](https://img.shields.io/badge/License-MIT-lightgrey)](LICENSE)

</div>

---

## 📖 Table of Contents

- [Overview](#overview)
- [Why GymRoutine?](#why-gymroutine)
- [Features](#features)
- [Screenshots](#screenshots)
- [Complete User Workflow](#complete-user-workflow)
- [Screen-by-Screen Guide](#screen-by-screen-guide)
- [Button Reference](#button-reference)
- [Data Storage](#data-storage)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Building from Source](#building-from-source)
- [Project Structure](#project-structure)
- [FAQ](#faq)
- [Contributing](#contributing)

---

## Overview

**GymRoutine** is a free, offline Android application built for people who take their training seriously. It gives you a clean, structured way to organise your workout program, log every set you perform in the gym, and review your progress over time — all without needing an account, an internet connection, or a subscription.

The app is built around a simple idea: **your training data belongs to you, lives on your device, and should be as easy to access as possible when you're standing at a barbell with chalk on your hands.**

### Who Is It For?

| User Type | How GymRoutine Helps |
|-----------|---------------------|
| 🏋️ Weightlifters | Build structured programs, log every set, track personal records |
| 💪 Gym beginners | Simple workflow with no learning curve — create, log, review |
| 🔁 Program followers | Recreate any training split with custom categories and exercises |
| 📊 Data-driven athletes | Review volume, session history, and strength trends over time |
| 📵 Offline-first users | No internet required, no account needed, no ads |

### What Problem Does It Solve?

Most free workout apps are cluttered with social feeds, calorie counters, AI coaches, and subscription walls. GymRoutine removes all of that noise and focuses exclusively on what matters in the gym:

- **Structured program design** — organise your training into routines and categories
- **Fast in-session logging** — tap an exercise, enter weight and reps, move on
- **Honest progress tracking** — see exactly what you lifted last time and beat it

---

## Why GymRoutine?

| Feature | GymRoutine | Many Competitors |
|---------|-----------|-----------------|
| 100% offline | ✅ | ❌ Requires internet |
| No account required | ✅ | ❌ Mandatory sign-up |
| No ads | ✅ | ❌ Ad-supported |
| No subscription | ✅ | ❌ Paywalled features |
| Custom exercises | ✅ Fully custom | ⚠️ Predefined database only |
| Custom categories | ✅ Any name you choose | ❌ Fixed Push/Pull/Legs only |
| Local data ownership | ✅ Stays on your device | ❌ Cloud-only storage |
| Clean focused UI | ✅ Workout-only design | ❌ Bloated with social features |

---

## Features

### 🗂️ Routine Management
- Create **unlimited** training routines with custom names
- Each routine holds as many categories as you need
- Edit or delete routines at any time
- View total categories and exercises at a glance from the home screen

### 📂 Exercise Categories
- Create fully **custom categories** inside each routine — name them anything: *Chest*, *Back*, *Legs*, *Power Day*, *Weak Points*, *Upper Body*, or anything that fits your program
- Rename categories whenever your training evolves
- Delete categories with automatic cleanup of all associated data
- Each category shows its exercise count at a glance

### 🏃 Custom Exercises
- Add **any exercise you can think of** inside any category — there is no predefined exercise database and no restrictions
- Edit exercise names at any time
- Delete exercises you no longer need
- View history for any individual exercise directly from the category screen

### ⏱️ Active Workout Session
- Select a category from your routine and start a live workout session
- A **live timer** counts your workout duration from the first second
- All exercises in the selected category load automatically
- Tap any exercise to expand its logging panel
- See your **last session's best weight and reps** as a reference before you start each exercise

### 📝 Set Logging
- Add as many sets as you need for each exercise
- Enter **weight (in kg)** and **reps** for every individual set
- Remove sets you added by mistake
- Each set is numbered automatically
- Data is entered as you go — nothing is lost if you close the app mid-session

### 🏁 Workout Summary
- When you finish, a **detailed completion screen** shows:
  - Category name
  - Workout duration (minutes)
  - Number of exercises completed
  - Total sets performed
  - Total volume lifted (weight × reps, summed across all sets)
- All data saves permanently to your device

### 📅 Exercise History
- Tap the history icon on any exercise to open its complete performance log
- Every previous session is shown in a clean **timeline layout**
- See date, sets performed, weight, reps, and total volume for each past session
- Personal bests for best weight and best reps are highlighted at the top

### 📈 Progress Tracking
- A dedicated Progress screen shows:
  - **Total workouts** completed all time
  - **This week's sessions** at a glance
  - **Number of active routines**
  - **Volume chart** — a bar chart of total volume lifted per session across your last 8 workouts
  - **Session frequency** comparison

### 🔍 Global Search
- Search across **routines, categories, and exercises** simultaneously with a single query
- Results are grouped by type and appear instantly as you type
- Tap any result to navigate directly to that item

### ⚙️ Settings
- Toggle **Dark Mode** on or off
- Access the built-in **How To Use** guide at any time
- View app version information

### 📚 Built-in How To Use Guide
- A beautiful **10-step in-app guide** explains the complete workflow
- Each step has an icon, description, and a code-style detail block with real examples
- Accessible any time from Settings → How To Use

---

## Screenshots

> _Screenshots coming soon. Build and run the project to see the app in action._

| Splash Screen | Home Screen | Routine Detail |
|:---:|:---:|:---:|
| _(Animated logo)_ | _(Routines + quick actions)_ | _(Categories list)_ |

| Category Screen | Active Workout | Workout Complete |
|:---:|:---:|:---:|
| _(Exercises list)_ | _(Live timer + set logging)_ | _(Summary + trophy)_ |

| Exercise History | Progress | Settings |
|:---:|:---:|:---:|
| _(Timeline layout)_ | _(Volume chart)_ | _(Dark mode + help)_ |

---

## Complete User Workflow

This section walks you through the **complete journey** from first launch to tracking long-term progress. Follow these steps in order and you will have a fully working training log in under five minutes.

---

### Step 1 — Open the App

When GymRoutine launches, you are greeted with an **animated splash screen** showing the app logo and tagline *"Plan. Train. Progress."*

After a brief moment, the app navigates automatically to the **Home Screen**, which becomes your central hub for everything.

**On first launch**, the Home Screen will be empty. The routine count shows **0** and there are no routine cards yet. This is expected — you begin by creating your first routine.

---

### Step 2 — Create a Routine

> **What is a Routine?**
> A routine is a named training program — the top-level container that holds all your workout categories. Think of it as your overall plan. Examples: *"My Workout Plan"*, *"5-Day Powerlifting Split"*, *"Hypertrophy Program"*, *"Summer Cut"*.

**How to create a routine:**

1. On the Home Screen, tap **+ New Routine** (the button in the middle of the screen)
2. You are taken to the **Create Routine Screen**
3. Type your routine name into the text field — e.g. `My Workout Plan`
4. Tap **Create Routine**
5. The app immediately takes you to the **Routine Detail Screen** for your new routine

You can create **as many routines as you like**. You might keep one for your current program and keep old ones for reference.

---

### Step 3 — Create Exercise Categories

> **What is a Category?**
> A category is a named group of exercises that live inside your routine. Categories let you organise your training by muscle group, movement type, or any other system you prefer. You define the names — there are **no restrictions** or predefined splits forced on you.

**Common category names:**

```
Chest          Shoulders       Upper Body
Back           Arms            Lower Body
Legs           Core            Power Day
Quads          Hamstrings      Weak Points
```

**How to create a category:**

1. Inside the **Routine Detail Screen**, tap the **＋ button** (top-right corner)
2. A dialog appears asking for a category name
3. Type the name — e.g. `Chest`
4. Tap **Save**
5. The category appears in your list immediately

Repeat this process to add as many categories as your routine requires. A typical 5-day program might have: Chest · Back · Legs · Shoulders · Arms.

Each category card shows:
- The category name
- How many exercises are inside it
- A colour-coded icon for quick visual identification

---

### Step 4 — Add Exercises to a Category

> **What is an Exercise?**
> An exercise is any movement you perform in the gym. There is no built-in database — you type whatever name you use. This means you can add machine-specific names, your own shorthand, or unusual movements without any restrictions.

**How to add an exercise:**

1. Tap a category card (e.g. *Chest*) to open the **Category Screen**
2. Tap the **＋ button** (top-right corner)
3. A dialog appears — type the exercise name, e.g. `Bench Press`
4. Tap **Save**
5. The exercise appears in the list numbered from 1

Add all the exercises you perform in that category. For example, under *Chest* you might add:

```
1. Bench Press
2. Incline Dumbbell Press
3. Cable Fly
4. Machine Chest Press
5. Dips
```

You can add exercises in any order and edit or delete them at any time.

---

### Step 5 — Go to the Gym

Once your routine is set up with categories and exercises, you are ready to train.

**To start a workout session:**

1. From the **Home Screen**, tap **Start Workout** (the large accent button in the centre)
2. You are taken to the **Start Workout Screen**

---

### Step 6 — Select a Routine and Category

On the **Start Workout Screen** you will see two steps:

**First — select your routine:**
- All your created routines are shown as cards
- Tap the routine you want to train with
- The card highlights with an accent border to confirm your selection

**Second — select a category:**
- Once a routine is selected, its categories appear below
- Each category card shows the category name and how many exercises it contains
- Tap the category you want to train today — e.g. *Chest*
- The app creates a new **workout session** automatically and opens the Active Workout Screen

> **Tip:** You pick one category per session. If you want to train both Chest and Back, start two separate sessions, or add all relevant exercises under a single combined category like *Upper Body*.

---

### Step 7 — Open an Exercise

The **Active Workout Screen** loads with:
- Your category name as the title
- A live timer counting your session duration in the top-right corner
- All exercises in the selected category listed as collapsed cards

**To start logging an exercise:**

1. Tap the exercise card (e.g. *Bench Press*)
2. The card **expands** to reveal the logging interface
3. If you have trained this exercise before, you will see a **"Last:"** hint showing your best set from the previous session — e.g. `Last: 80kg × 8`

This previous session reference is displayed so you always know what you need to beat or match.

---

### Step 8 — Log Your Sets

Inside an expanded exercise card you will see:

| Column | Description |
|--------|-------------|
| **SET** | The set number, auto-assigned (1, 2, 3…) |
| **KG** | Enter the weight you lifted in kilograms |
| **REPS** | Enter how many reps you completed |

**How to log a set:**

1. Tap the **KG** field and enter the weight — e.g. `80`
2. Tap the **REPS** field and enter the reps — e.g. `8`
3. That set is recorded

**To add more sets:**
- Tap **+ Add Set** at the bottom of the exercise card
- A new empty set row appears

**To remove a set you added by mistake:**
- Tap the **– (minus) button** on the right side of any set row
- The row is removed and set numbers update automatically

There is no "save per exercise" button — data is held in memory as you work through your session. Everything is saved permanently when you finish the whole workout.

> **Tip:** You do not have to log every exercise. If you skip one, simply leave it collapsed and it will not be included in your saved log.

---

### Step 9 — Finish and Save Your Workout

When you are done training:

1. Tap the **Finish Workout** button at the top of the Active Workout Screen
2. A confirmation dialog appears: *"Your workout will be saved. Ready to finish?"*
3. Tap **Finish**

The app saves the following data permanently:

- **Session date** — the calendar date of the workout
- **Start time** — when you tapped Start Workout
- **End time** — when you tapped Finish
- **Duration** — calculated automatically
- **Every exercise** that had at least one set with a valid weight and reps
- **Every set** with its set number, weight (kg), and reps

You are then taken to the **Workout Complete Screen**, which shows your full session summary:
- 🏆 **Trophy animation** celebrating the completed workout
- Category name
- Duration in minutes
- Number of exercises completed
- Total sets performed
- Total volume lifted (in kg)

Tap **Back to Home** to return to the home screen.

---

### Step 10 — View Exercise History

After completing workouts, you can review the history of any individual exercise at any time — whether you are at home planning your next session or at the gym warming up.

**Two ways to access exercise history:**

1. **From the Category Screen** — tap the **clock/history icon** (🕐) on the right side of any exercise card
2. **From Search** — search for the exercise name and tap the result

**The Exercise History Screen shows:**

- **Best Weight** — the heaviest weight you have ever lifted for this exercise
- **Best Reps** — the highest rep count ever recorded for this exercise
- **Total Sessions** — how many times you have logged this exercise
- **Full timeline** — every past session listed chronologically from most recent to oldest

Each session entry in the timeline shows:
- The date of the session
- How many sets were performed
- Each set's weight × reps
- Total session volume for that exercise

This timeline makes it easy to see whether you are progressing — heavier weights, more reps, or higher volume over time indicates improvement.

---

### Step 11 — Track Progress

The **Progress Screen** gives you a bird's-eye view of your overall training activity.

**How to access it:**
- Tap the **Progress** button on the Home Screen (the button with the trending-up icon)

**What it shows:**

| Metric | Description |
|--------|-------------|
| **Total Workouts** | All-time number of completed workout sessions |
| **This Week** | Sessions completed in the last 7 days |
| **Active Routines** | Total number of routines you have created |
| **Volume Chart** | Bar chart showing total volume (kg) for each of your last 8 sessions |
| **Frequency Panel** | Side-by-side view of this week vs. all-time session counts |

**Understanding the Volume Chart:**

Each bar represents one workout session. The height of the bar corresponds to total volume — the sum of (weight × reps) across every set in that session. A rising chart over time means you are progressively overloading, which is the fundamental driver of strength and muscle growth.

---

## Screen-by-Screen Guide

### 🏠 Home Screen

The Home Screen is the main hub of GymRoutine. Everything in the app starts here.

**Information displayed:**
- Greeting with the current day and date
- Routines counter — total number of routines you have created
- Last Workout indicator — the date of your most recent workout session

**Action buttons:**

| Button | Location | Purpose |
|--------|----------|---------|
| **Start Workout** | Centre, large accent button | Begins the workout selection flow — choose routine → choose category → start logging |
| **+ New Routine** | Left side of the quick actions row | Opens the Create Routine Screen |
| **Progress** | Right side of the quick actions row | Opens the Progress Screen |
| **🔍 Search** | Top-right, circular button | Opens the Global Search Screen |
| **⚙️ Settings** | Top-right, circular button (next to search) | Opens the Settings Screen |

**Routine Cards:**

Each card in the scrollable list represents one of your saved routines. Tapping a card opens the Routine Detail Screen for that routine.

Every routine card shows:
- Routine name (bold)
- Creation date
- Number of categories
- Number of exercises (if any exist)
- A left-edge accent bar for visual distinction
- A **⋮ (three-dot) menu** — tap to reveal a Delete option

---

### ➕ Create Routine Screen

**Purpose:** Name and create a new training routine.

**What you see:**
- A circular icon with a barbell graphic
- A text field labelled with placeholder text *"e.g. Push Pull Legs"*
- An error message if you try to save an empty name

**Buttons:**

| Button | Purpose |
|--------|---------|
| **← Back** | Returns to the Home Screen without saving |
| **Create Routine** | Saves the routine and navigates directly to its Routine Detail Screen |

> The Create Routine button is disabled until you type at least one character. Once the routine is saved you are taken straight to its detail screen so you can immediately add categories.

---

### 📋 Routine Detail Screen

**Purpose:** Manage the exercise categories within a single routine.

**What you see:**
- The routine name as the screen title
- Two stat chips: **Categories** count and **Exercises** count (total across all categories)
- A scrollable list of all category cards in this routine
- An empty state graphic if no categories have been added yet

**Buttons:**

| Button | Location | Purpose |
|--------|----------|---------|
| **← Back** | Top-left | Returns to the Home Screen |
| **＋ (Add Category)** | Top-right | Opens the Add Category dialog |
| **Category card** | Anywhere on the card | Opens the Category Screen for that category |
| **⋮ (three-dot menu)** | Right side of each card | Opens Rename / Delete options for that category |

**Add Category Dialog:**
- Text field with placeholder *"e.g. Chest, Back, Legs, Shoulders"*
- **Save** button — creates the category
- **Cancel** button — dismisses without saving

**Rename Category Dialog:**
- Same layout, pre-filled with the current category name
- Edit the text and tap **Save** to update

> Deleting a category also deletes all exercises inside it and all workout logs for those exercises. This action cannot be undone.

---

### 🗂️ Category Screen (Exercise List)

**Purpose:** View and manage exercises inside a single category. This is also the screen you use when planning your program — adding every movement you want to perform.

**What you see:**
- The category name as the screen title
- An **Exercises** stat chip showing the count
- A numbered list of all exercises in this category

**Buttons:**

| Button | Location | Purpose |
|--------|----------|---------|
| **← Back** | Top-left | Returns to Routine Detail Screen |
| **＋ (Add Exercise)** | Top-right | Opens the Add Exercise dialog |
| **🕐 History icon** | Right side of each exercise row | Opens the Exercise History Screen for that exercise |
| **⋮ (three-dot menu)** | Right side of each exercise row | Opens Edit / Delete options |

**Add Exercise Dialog:**
- Text field with placeholder *"e.g. Bench Press, Squat, Deadlift"*
- **Save** — adds the exercise to the bottom of the list
- **Cancel** — dismisses without saving

**Edit Exercise Dialog:**
- Pre-filled with the current exercise name
- Edit and tap **Save** to update

---

### ▶️ Start Workout Screen

**Purpose:** Select which routine and category you want to train in this session.

**Flow:**

1. **Step 1 — Select Routine:** A list of all your routines is shown. Tap the routine you want to use. The selected routine highlights with an accent border and a dot indicator.

2. **Step 2 — Select Category:** Once a routine is selected, its categories appear below. Each card shows the category name, exercise count, and a coloured icon.

3. **Tapping a category** immediately creates a new workout session record and navigates to the Active Workout Screen.

**Buttons:**

| Button | Location | Purpose |
|--------|----------|---------|
| **← Back** | Top-left | Returns to Home Screen |
| **Routine card** | Main list | Selects that routine and reveals its categories |
| **Category card → Start** | Category list | Starts the workout session for that category |

---

### 🏋️ Active Workout Screen

**Purpose:** The main workout logging interface. This is the screen you use while training.

**What you see:**
- Category name and *"Active Workout"* subtitle
- A **live timer** in the top-right (counts up from 00:00)
- **Finish Workout** button at the top
- All exercises in the selected category as expandable cards

**Each exercise card (collapsed) shows:**
- Exercise name
- *"Last: Xkg × Y"* hint if you have previous data for this exercise

**Each exercise card (expanded) shows:**
- Column headers: SET · KG · REPS
- Individual set rows — numbered circles with weight and reps input fields
- **+ Add Set** button
- **− (minus) button** on each set row (visible when 2 or more sets exist)

**Buttons:**

| Button | Location | Purpose |
|--------|----------|---------|
| **✕ (Close)** | Top-left | Exits the workout without saving (returns to Home) |
| **Finish Workout** | Top of screen | Opens the confirmation dialog to save and end session |
| **Exercise card (tap)** | Anywhere on card header | Toggles the logging panel open or closed |
| **+ Add Set** | Bottom of expanded exercise | Adds a new empty set row |
| **− (Remove Set)** | Right side of set row | Removes that specific set |
| **Finish** (in dialog) | Confirmation dialog | Saves all data and navigates to Workout Complete |
| **Continue** (in dialog) | Confirmation dialog | Dismisses dialog, returns to workout |

> **Important:** Only sets with both a valid weight AND valid reps entered will be saved. Empty or zero-value sets are automatically excluded.

---

### 🏆 Workout Complete Screen

**Purpose:** Celebrate your completed session and display a summary of what you achieved.

**What you see:**
- Animated trophy icon (spring-bounce animation on entry)
- *"Workout Complete!"* heading
- Category name in accent colour
- Four stat tiles:

| Stat | What It Means |
|------|--------------|
| Duration (min) | Total time from Start to Finish |
| Exercises | Number of exercises that had at least one logged set |
| Total Sets | Sum of all logged sets across all exercises |
| Volume (kg) | Sum of (weight × reps) for every set in the session |

**Buttons:**

| Button | Purpose |
|--------|---------|
| **Back to Home** | Navigates to the Home Screen. All data is already saved. |

---

### 📅 Exercise History Screen

**Purpose:** Review the complete performance history for a single exercise across all time.

**What you see:**
- The exercise name as the screen title
- Three stat chips at the top:

| Chip | Meaning |
|------|---------|
| **Best Weight** | Heaviest weight ever recorded for this exercise |
| **Best Reps** | Highest rep count ever recorded for this exercise |
| **Sessions** | Total number of sessions this exercise has been logged in |

- A vertical **timeline** of all sessions from most recent to oldest
- Each timeline entry shows a date label, set count badge, individual set rows (Set N: Xkg × Y reps), and total volume for that session

**Buttons:**

| Button | Purpose |
|--------|---------|
| **← Back** | Returns to the Category Screen |

---

### 📈 Progress Screen

**Purpose:** Monitor your overall training activity and volume trends.

**What you see:**

**Stats row (top):**
- Total Workouts (all time)
- This Week (last 7 days)
- Active Routines

**Volume Chart:**
- A custom bar chart showing your last 8 workout sessions
- Each bar's height represents the total volume (kg) for that session
- Volume values are labelled on taller bars
- Session dates are shown on the x-axis

**Frequency Panel:**
- *Avg This Week* — sessions in the past 7 days
- *All Time* — total session count

**Buttons:**

| Button | Purpose |
|--------|---------|
| **← Back** | Returns to the Home Screen |

---

### 🔍 Search Screen

**Purpose:** Find any routine, category, or exercise instantly across your entire data set.

**What you see:**
- A search bar with a magnifying glass icon (auto-focused on entry)
- A spinner inside the search bar while results are loading
- Results grouped into three sections: Routines · Categories · Exercises
- Each section shows a count badge next to its header

**Behaviour:**
- Results appear as you type — no need to press Search or Enter
- Tapping a routine result navigates to that routine's detail screen
- Tapping a category result navigates to that category's exercise list
- Tapping an exercise result opens that exercise's history
- The **✕ (clear)** button inside the search bar clears the query

**Buttons:**

| Button | Purpose |
|--------|---------|
| **← Back** | Returns to the Home Screen |
| **✕ (clear)** | Clears the search query |
| **Result row** | Navigates directly to that item |

---

### ⚙️ Settings Screen

**Purpose:** App preferences and help resources.

**Sections:**

**Appearance**
- **Dark Mode toggle** — switches the app between dark and light theme. The toggle shows the current state. Dark mode is the default.

**Help**
- **How To Use** → navigates to the built-in 10-step guide

**About**
- Version: 1.0.0
- App name: GymRoutine

**Buttons:**

| Button | Purpose |
|--------|---------|
| **← Back** | Returns to the Home Screen |
| **Dark Mode toggle** | Switches theme |
| **How To Use** | Opens the How To Use Screen |

---

### 📚 How To Use Screen

**Purpose:** A built-in, beautifully designed reference guide that explains the complete app workflow with icons, step badges, and examples.

**What you see:**
- A hero header card: *"Quick Start Guide — Follow these 10 steps to master GymRoutine"*
- A vertical timeline of 10 steps
- Each step has: a numbered badge, a colour-coded icon, a step label, a plain-language description, and (where relevant) a monospace detail block showing real examples

**The 10 steps covered:**
1. Create a Routine
2. Create Categories
3. Add Exercises
4. Go To The Gym
5. Select a Category
6. Open an Exercise
7. Log Your Sets
8. Finish & Save
9. View History
10. Track Progress

**Buttons:**

| Button | Purpose |
|--------|---------|
| **← Back** | Returns to the Settings Screen |

---

## Button Reference

A complete reference of every interactive button in the app.

| Button Label | Screen | Action |
|---|---|---|
| **Start Workout** | Home | Opens Start Workout screen |
| **+ New Routine** | Home | Opens Create Routine screen |
| **Progress** | Home | Opens Progress screen |
| **🔍 Search** | Home | Opens Search screen |
| **⚙️ Settings** | Home | Opens Settings screen |
| **⋮ on Routine Card** | Home | Shows Delete menu for that routine |
| **Create Routine** | Create Routine | Saves and navigates to Routine Detail |
| **← Back** | Any screen | Returns to the previous screen |
| **＋ (Add Category)** | Routine Detail | Opens Add Category dialog |
| **⋮ on Category Card** | Routine Detail | Shows Rename / Delete menu |
| **＋ (Add Exercise)** | Category Screen | Opens Add Exercise dialog |
| **🕐 History** | Category Screen | Opens Exercise History for that exercise |
| **⋮ on Exercise Row** | Category Screen | Shows Edit / Delete menu |
| **Routine Card** | Start Workout | Selects the routine, reveals categories |
| **Category Card** | Start Workout | Creates session, opens Active Workout |
| **Exercise Card (tap)** | Active Workout | Expands or collapses the set logging panel |
| **+ Add Set** | Active Workout | Adds a new set row to the exercise |
| **− Remove Set** | Active Workout | Removes that set row |
| **Finish Workout** | Active Workout | Opens finish confirmation dialog |
| **Finish** (dialog) | Active Workout | Saves workout, opens Workout Complete |
| **Continue** (dialog) | Active Workout | Dismisses dialog, continues workout |
| **✕ Close** | Active Workout | Exits workout without saving |
| **Back to Home** | Workout Complete | Returns to Home Screen |
| **✕ Clear** | Search | Clears the search query |
| **Search result row** | Search | Navigates to that item |
| **Dark Mode toggle** | Settings | Switches app theme |
| **How To Use** | Settings | Opens How To Use screen |

---

## Data Storage

### Everything Stays on Your Device

GymRoutine uses **Room**, Android's local SQLite database library, to store all your data directly on your phone. This means:

- ✅ **No internet connection is ever required** — the app works fully offline, always
- ✅ **No account or sign-up needed** — your data is not linked to any email or profile
- ✅ **No data is sent to any server** — nothing leaves your device
- ✅ **No cloud dependency** — your workout history cannot be lost due to a server outage
- ✅ **Works in airplane mode, underground, or anywhere with no signal**

### What Gets Stored

| Data | Stored When |
|------|------------|
| Routine name and creation date | When you create a routine |
| Category names and order | When you create or rename categories |
| Exercise names and order | When you add exercises to a category |
| Session start time and date | The moment you tap a category on Start Workout |
| Session end time | When you tap Finish Workout |
| Set number, weight, and reps | When you tap Finish Workout |

### Database Schema

The app uses five related tables:

```
routines
  └── workout_days (exercise categories)
        └── exercises
              └── workout_sessions
                    └── exercise_logs (individual sets)
```

All relationships use **cascade deletion** — if you delete a routine, all its categories, exercises, sessions, and logs are automatically cleaned up.

---

## Tech Stack

| Layer | Technology | Version |
|-------|-----------|---------|
| Language | Kotlin | 2.0.21 |
| UI Framework | Jetpack Compose | BOM 2024.10.00 |
| Design System | Material 3 | Latest |
| Architecture | MVVM + Clean Architecture | — |
| Dependency Injection | Hilt | 2.51.1 |
| Local Database | Room | 2.6.1 |
| Navigation | Navigation Compose | 2.8.3 |
| Async | Kotlin Coroutines + StateFlow | 1.9.0 |
| Preferences | DataStore | 1.1.1 |
| Build System | Gradle (KTS) | 8.9 |
| Android Gradle Plugin | AGP | 8.5.2 |
| Min SDK | Android 8.0 (Oreo) | API 26 |
| Target SDK | Android 15 | API 35 |

### Architecture Overview

```
app/
├── data/
│   ├── local/
│   │   ├── dao/           # Room DAO interfaces
│   │   ├── database/      # Room database definition
│   │   └── entities/      # Room entity classes
│   └── repository/        # Repository implementations
├── di/                    # Hilt dependency injection modules
├── domain/
│   ├── model/             # Pure domain models
│   ├── repository/        # Repository interfaces
│   └── usecase/           # (reserved for future use cases)
└── presentation/
    ├── components/        # Reusable Compose components
    ├── navigation/        # Navigation graph and routes
    ├── screens/           # One folder per screen
    ├── theme/             # Colors, Typography, Theme
    └── viewmodel/         # ViewModels (one per screen)
```

---

## Installation

### Prerequisites

- Android device running **Android 8.0 (API 26) or higher**
- Android Studio **Hedgehog (2023.1.1)** or newer (for building from source)

### Install from Release

1. Download the latest **GymRoutine.apk** from the [Releases](../../releases) page
2. On your Android device, go to **Settings → Security** (or Privacy) and enable **Install unknown apps** for your browser or file manager
3. Open the downloaded APK file
4. Tap **Install**
5. Open **GymRoutine** from your app drawer

---

## Building from Source

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/GymRoutine.git
cd GymRoutine
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select **File → Open**
3. Navigate to the cloned `GymRoutine` folder and click **OK**
4. Wait for the Gradle sync to complete (dependencies will download automatically)

### 3. Run the App

**On a physical device:**
1. Connect your Android device via USB
2. Enable **USB Debugging** on the device (Settings → Developer Options → USB Debugging)
3. Select your device in the toolbar
4. Click the **▶ Run** button or press `Shift + F10`

**On an emulator:**
1. Open **Device Manager** in Android Studio
2. Create a virtual device with API 26 or higher
3. Start the emulator and click **▶ Run**

### 4. Build a Release APK

```bash
./gradlew assembleRelease
```

The APK will be generated at:
```
app/build/outputs/apk/release/app-release.apk
```

---

## Project Structure

```
GymRoutine/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── kotlin/com/gymroutine/app/
│   │       │   ├── GymRoutineApp.kt           # Application class (Hilt entry point)
│   │       │   ├── MainActivity.kt            # Single Activity host
│   │       │   ├── data/
│   │       │   │   ├── local/
│   │       │   │   │   ├── dao/
│   │       │   │   │   │   ├── ExerciseDao.kt
│   │       │   │   │   │   ├── ExerciseLogDao.kt
│   │       │   │   │   │   ├── RoutineDao.kt
│   │       │   │   │   │   ├── WorkoutDayDao.kt
│   │       │   │   │   │   └── WorkoutSessionDao.kt
│   │       │   │   │   ├── database/
│   │       │   │   │   │   └── GymRoutineDatabase.kt
│   │       │   │   │   └── entities/
│   │       │   │   │       ├── ExerciseEntity.kt
│   │       │   │   │       ├── ExerciseLogEntity.kt
│   │       │   │   │       ├── RoutineEntity.kt
│   │       │   │   │       ├── WorkoutDayEntity.kt
│   │       │   │   │       └── WorkoutSessionEntity.kt
│   │       │   │   └── repository/
│   │       │   │       ├── ExerciseLogRepositoryImpl.kt
│   │       │   │       ├── ExerciseRepositoryImpl.kt
│   │       │   │       ├── RoutineRepositoryImpl.kt
│   │       │   │       ├── WorkoutDayRepositoryImpl.kt
│   │       │   │       └── WorkoutSessionRepositoryImpl.kt
│   │       │   ├── di/
│   │       │   │   ├── DatabaseModule.kt
│   │       │   │   └── RepositoryModule.kt
│   │       │   ├── domain/
│   │       │   │   ├── model/
│   │       │   │   │   ├── Exercise.kt
│   │       │   │   │   ├── ExerciseLog.kt
│   │       │   │   │   ├── Routine.kt
│   │       │   │   │   ├── SetEntry.kt
│   │       │   │   │   ├── WorkoutDay.kt
│   │       │   │   │   └── WorkoutSession.kt
│   │       │   │   └── repository/
│   │       │   │       ├── ExerciseLogRepository.kt
│   │       │   │       ├── ExerciseRepository.kt
│   │       │   │       ├── RoutineRepository.kt
│   │       │   │       ├── WorkoutDayRepository.kt
│   │       │   │       └── WorkoutSessionRepository.kt
│   │       │   └── presentation/
│   │       │       ├── components/
│   │       │       │   └── CommonComponents.kt
│   │       │       ├── navigation/
│   │       │       │   ├── GymRoutineNavGraph.kt
│   │       │       │   └── NavRoutes.kt
│   │       │       ├── screens/
│   │       │       │   ├── exercise/
│   │       │       │   │   └── ExerciseHistoryScreen.kt
│   │       │       │   ├── home/
│   │       │       │   │   └── HomeScreen.kt
│   │       │       │   ├── howto/
│   │       │       │   │   └── HowToUseScreen.kt
│   │       │       │   ├── progress/
│   │       │       │   │   └── ProgressScreen.kt
│   │       │       │   ├── routine/
│   │       │       │   │   ├── CreateRoutineScreen.kt
│   │       │       │   │   └── RoutineDetailScreen.kt
│   │       │       │   ├── search/
│   │       │       │   │   └── SearchScreen.kt
│   │       │       │   ├── settings/
│   │       │       │   │   └── SettingsScreen.kt
│   │       │       │   ├── splash/
│   │       │       │   │   └── SplashScreen.kt
│   │       │       │   └── workout/
│   │       │       │       ├── ActiveWorkoutScreen.kt
│   │       │       │       ├── StartWorkoutScreen.kt
│   │       │       │       ├── WorkoutCompleteScreen.kt
│   │       │       │       └── WorkoutDayScreen.kt
│   │       │       ├── theme/
│   │       │       │   ├── Color.kt
│   │       │       │   ├── Theme.kt
│   │       │       │   └── Type.kt
│   │       │       └── viewmodel/
│   │       │           ├── ActiveWorkoutViewModel.kt
│   │       │           ├── CreateRoutineViewModel.kt
│   │       │           ├── ExerciseHistoryViewModel.kt
│   │       │           ├── HomeViewModel.kt
│   │       │           ├── ProgressViewModel.kt
│   │       │           ├── RoutineViewModel.kt
│   │       │           ├── SearchViewModel.kt
│   │       │           ├── SettingsViewModel.kt
│   │       │           ├── StartWorkoutViewModel.kt
│   │       │           ├── WorkoutCompleteViewModel.kt
│   │       │           └── WorkoutDayViewModel.kt
│   │       ├── res/
│   │       │   ├── drawable/
│   │       │   ├── mipmap-*/
│   │       │   └── values/
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## FAQ

<details>
<summary><strong>Do I need an internet connection to use GymRoutine?</strong></summary>

No. GymRoutine is **100% offline**. All data is stored locally on your device using a SQLite database. The app never makes any network requests and will work perfectly with no signal, in airplane mode, or anywhere with no connectivity.

</details>

<details>
<summary><strong>Do I need to create an account?</strong></summary>

No. There is no account, login screen, email address, or sign-up process. You open the app and start using it immediately.

</details>

<details>
<summary><strong>Will my workout history be saved if I close the app?</strong></summary>

Yes, with one important caveat: your workout data is saved **when you tap Finish Workout**, not continuously as you type. If you force-close the app mid-session before tapping Finish, the session data for that session will be lost. As long as you complete your session normally, everything is saved permanently.

</details>

<details>
<summary><strong>Can I create my own exercises?</strong></summary>

Yes — that is the entire design philosophy. There is no predefined exercise database. Every exercise you add is one you type yourself. This means you can use any name, any language, and any movement — including machine-specific names, your own shorthand, or unusual exercises not found in any database.

</details>

<details>
<summary><strong>Can I have more than one routine?</strong></summary>

Yes. You can create as many routines as you like. Many users keep their current program as one routine and keep older programs for reference.

</details>

<details>
<summary><strong>Can I edit or rename categories after creating them?</strong></summary>

Yes. On the Routine Detail Screen, tap the ⋮ (three-dot menu) on any category card and select **Rename**. You can rename a category at any time without losing any exercise or history data.

</details>

<details>
<summary><strong>What happens if I delete a category?</strong></summary>

Deleting a category permanently removes: the category itself, all exercises inside it, all workout sessions linked to those exercises, and all logged sets. **This cannot be undone.** The app uses cascade deletion, so the database stays clean automatically.

</details>

<details>
<summary><strong>Can I view old workout records for an exercise?</strong></summary>

Yes. Tap the **🕐 history icon** on any exercise in the Category Screen, or search for the exercise and tap the result. The Exercise History Screen shows your complete performance log for that exercise in a timeline layout, from most recent to oldest.

</details>

<details>
<summary><strong>How is "Volume" calculated?</strong></summary>

Volume = weight × reps, summed across all sets in a session. For example: if you do 3 sets of 80kg × 8 reps, your volume for that exercise in that session is 80 × 8 × 3 = 1,920 kg. The Progress screen charts this total volume per session over time.

</details>

<details>
<summary><strong>Can I use the app in kilograms and pounds?</strong></summary>

The current version uses kilograms (kg) as the weight unit. You can still enter any number — if you think in pounds, simply enter your pound values and the app will store and display them as-is. A unit toggle is planned for a future update.

</details>

<details>
<summary><strong>What Android version does the app require?</strong></summary>

GymRoutine requires **Android 8.0 (Oreo, API level 26)** or higher. It is compatible with the vast majority of Android devices currently in use.

</details>

<details>
<summary><strong>Where is my data stored? Can I back it up?</strong></summary>

Your data is stored in a Room (SQLite) database in the app's private storage directory on your device. It is included in Android's standard backup mechanism if you have Google Backup enabled. Manual backup and export features are on the roadmap for a future version.

</details>

<details>
<summary><strong>Is there a limit to how many routines, categories, or exercises I can create?</strong></summary>

No. The app imposes no artificial limits. The only practical limit is your device's available storage, which is effectively unlimited for this type of text data.

</details>

<details>
<summary><strong>Can I reorder my exercises or categories?</strong></summary>

Categories and exercises are displayed in the order they were created. A drag-to-reorder feature is planned for a future update.

</details>

---

## Contributing

Contributions are welcome. To get started:

1. **Fork** this repository
2. **Clone** your fork: `git clone https://github.com/yourusername/GymRoutine.git`
3. **Create a feature branch**: `git checkout -b feature/your-feature-name`
4. **Make your changes** and ensure the project builds without errors
5. **Commit** with a descriptive message: `git commit -m "Add: drag-to-reorder categories"`
6. **Push** to your fork: `git push origin feature/your-feature-name`
7. **Open a Pull Request** against the `main` branch of this repository

### Code Style

- Follow existing Kotlin and Jetpack Compose conventions used throughout the project
- Each screen should have its own file in the appropriate `screens/` subfolder
- ViewModels must use `StateFlow` and be injected via Hilt
- All database operations must go through the repository layer
- New Room entities require a database migration

### Reporting Issues

If you find a bug or want to request a feature, please [open an issue](../../issues) with:
- A clear title and description
- Steps to reproduce (for bugs)
- Android version and device model (for bugs)

---

<div align="center">

**Built with ❤️ for athletes who just want to train.**

*GymRoutine — Plan. Train. Progress.*

</div>
