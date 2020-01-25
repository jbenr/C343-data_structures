# distribution
Distribution Repository for C343-Sp2020

## Instructions for pulling starter files



# Git Setup

Git is a version control system which can be used to maintain different version of your code. In case your current version is broken, but the previous version worked better, Git allows you to roll back to older versions of your program.


For C343, we will also use Git to download new assignments and submit completed assignments to an Autograder.


Since assignment submission depends on Git, our goal is to get everyone setup with it as soon as possible. The following set of instructions should give you a basic understanding for how to use Git and how to avoid running into common Git issues. If you run into any issues during these instructions, please contact someone from the staff right away.


<br />

## Note for Windows Users

Before you read the instructions, you will need to install `git bash`, a program that lets you interact with Git the same way Linux and Mac users do.

**Installation Steps**

1. Navigate to

https://git-scm.com/downloads

2. Click on the link for the Windows download.

3. Follow the installation instructions. Default options are normally appropriate.

Make sure the boxes are checked for `Use git from the Windows Command Prompt` and `Checkout Windows-style, commit Unix-style line endings`.

<br />

## Understanding the Command Line

This might seem intimidating (which is normal), but we will just present the basic tools needed for C343.

Linux and MacOS users should now have their terminal opened, Windows users should now have `git bash` opened.

Using `git` requires students to be able to navigate their system using the command line. You will need the following programs to do that:

`ls`: stands for "list files" and lists all files in your current directory. Try running this in your terminal to by just typing `ls`.

`pwd`: stands for "print working directory". Typing `pwd` in your terminal should tell you the path to the current directory.

`cd`: stands for "change directory". This program will allow you to move into directories. The following shows an example of using `ls` and `pwd` to navigate in your system.

	$ pwd

	/home/UI # This is the path of the current directory

	$ ls

	C343-sp2020     programming-memes     other-stuff

	
The above prompt shows a usage of `ls` which shows 3 directories. To navigate into the C343-sp2020,
we do the following:

	

	$ cd C343-sp2020

	$ pwd

	/home/UI/C343-sp2020

	

Note that after `cd C343-sp2020`, `pwd` now says we are inside the `C343-sp2020` directory. 
To go up directory, we do the following:

	

	$ cd ../

	$ pwd 

	/home/UI # We are back where we were!

	

Note that if you know a path to some directory, you can `cd` directly to it instead 
of doing it step by step.

	

	$ pwd 

	/home/UI # Just to show where we currently are

	$ cd C343-sp2020/exams/final/solutions

	$ pwd

	/home/UI/C343-sp2020/exams/final/solutions # We have directly jumped to the final solutions directory!


There are many other commands, such as `rm` for removing, `mv` for moving, etc. You will probably need to use these when the time comes, but for the time being, the above three should do.


<br />


## Understanding Git

<details>

<summary>Optional Reading</summary>


<p>

As mentioned earlier, Git is a version control system. It maintains “snapshots” of the state of the files being tracked by Git and keeps a history of all the snapshots. Metaphorically, you can imagine it as taking a screenshot of the entirety of a tracked file and saving it. You continue to take screenshots as you edit the file, and soon enough, you have a large collection of screenshots. If you want to go back to the very beginning of the file’s lifespan, you can pull up that very first screenshot you took, and restore the file to that state.


There are four main parts to a Git project:


- Working Directory

- Staging Area

- Local Repository

- Remote Repository


<br />


The working directory is where everything in your project is located, even files that you don’t want to include in your repository.


You can imagine the staging area as the stage where all the files that you want to be snapshotted are located. A file will not be included in the snapshot if you do not add it to the staging area. You can imagine it as a photo booth of sorts. If the file isn’t in the photo booth, then it won’t be in the picture.


The **local repository** is where all the committed files are located. It’s where the snapshots end up going. In the photo booth analogy, you can imagine this to be an album where the photos are stored. When you take that picture in the photo booth, then those pictures of the files can then be sent to the repository, where it will be stored for future reference.


The **remote repository** is on Github. In our analogy, you can imagine it as a cloud-based server, where you store the photos that you took. That allows other users to be able to essentially clone the album you have and create one of their own, just like yours. If they make any changes, they can push those changes onto the remote repository (assuming you give them permission), and you can then update your album with whatever they added/changed.

</p>

</details>

<br />

## Git Commands

*(all commands are preceded by* `git `*)*

- `add `: adds files to your staging area. You can follow add with a file name to add a specific file to the staging area, but typically you follow it with `.` (adds whatever files are in the current directory you are in) or with `--all` (adds all files in the entire working directory).

- `commit -m “<message>” `: commits the files in the staging area to the local repository. In our analogy, this would take that photo of all the files in the photo booth and store that snapshot. The `-m` option stands for message and is required. After the `-m`, add a comment in double quotes to describe what was committed.

- `push `: pushes the commit to the remote repository. In other words, you’ve uploaded your commit to the cloud-based server in our analogy.

- `clone <https link> `: if you have a remote repository on GitHub (in this case the one you should have created for this class), you’ll have to create a local copy of it on your computer. You can find the https link by going to your repository and clicking on the green button that says `Clone or download`. Make sure it says `Clone with HTTPS` and not SSH. Copy that link to your clipboard and paste it after the clone command.

- `pull `: assuming your local repository is linked with some remote repository, the pull command will update your local repository so that it is synced up with the remote repository. It is important that if you have two local repositories (most likely on separate machines) that are linked to the same remote repository that they are synced up on the right snapshot. Otherwise you’ll run into issues pushing as they are working based on different points in history and you won’t be able to push.

- `remote `: this command is followed by another command to determine what you want to do with the remote. Your repository can have more than one remote repository (you’ve connected your album to more than one cloud server). The `add` command, for example, connects your local repository to a given remote repository link. `rename` allows you to rename a remote repository so it makes it easier for you to differentiate between multiple remotes.

<br />

## Starter Files for Assignments and Labs

Before we start, you will need a copy of a starter C343 directory on your machine.

First, navigate to a directory where you would like to keep your C343 assignments, and then type the following command (the $ just means the command line, you don’t need to type it):

`$ git clone https://github.iu.edu/C343-SP2020/distribution.git`

This downloads our starter files into your directory. By default, Git will remember the link we provided above as `origin`. Let’s rename this to `distribution` since we will be distributing starter files for all assignments through the link above:

`$ git remote rename origin distribution`

At this point, everything you need to download new assignments is ready. Whenever there are new files uploaded to the starter repository, you can download them to your machine using the following command:

`$ git pull distribution master`

<br />

## Making Your Own Repository

For the last step, you will be setting up your own repository where you will upload you completed programs, which then will be retrieved by the Autograder for grading.

First, you should have been added as a collaborator to a repository named `[your-iu-username]-submission` under the C343-SP2020 organization. You should go and copy the link to this repository, and give this repository a name using git by using the following command:

`$ git remote add submission https://github.iu.edu/C343-SP2020/[your-iu-username]-submission.git`

Verify that everything is setup correctly by typing the following command:

`$ git remote -v`

You should see the following result:

    distribution https://github.iu.edu/C343-SP2020/distribution.git

    (fetch)

    distribution https://github.iu.edu/C343-SP2020/distribution.git

    (push)

    submission https://github.iu.edu/C343-SP2020/[username]-submission.git

    (fetch)

    submission https://github.iu.edu/C343-SP2020/[username]-submission.git

    (push)

<br />

## Standard Work-flow

A sample sequence of commands for your work-flow may look a little something like this:

	

	$ cd /path/to/local/repository

	# cd into the directory you have for this class

	$ git pull submission master

	# Make sure you're up to date with your submission repo so that you don't run into push issues later.

	$ git pull distribution master

	# Pulls the new starter files that have been uploaded.

	# At this point, you can begin working on your assignment. When you want to commit the changes you have made,

	# (and remember, commit often so that you have more snapshots to jump back to if you mess up somewhere), do the following:

	$ git add --all

	# Add all your files to the staging area to be prepared for a commit.

	$ git commit -m "some nicely detailed message here to show you aren't lazy"

	# Save that snapshot by committing it.

	$ git push submission master

	# Push those changes to your submission repo (NOT the distribution repo).
