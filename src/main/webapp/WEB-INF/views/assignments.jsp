<%@ include file="init.jsp"%>

<nrtl:pageTemplate>

  <nrtl:header>
    <h1>Assignment</h1>
  </nrtl:header>

  <div class="row">
    <div class="col-md-12">
      <h3>Story</h3>
      <p>Our client, Benson ordered an application from Warden of the Internet & Co. to evaluate the work quality of his employees.</p>
      <p>Unfortunately Warden of the Internet was unable to complete this development due to the actions of Mordecai and Rigby. Finally Benson came to us
      because he knows that we always get the job done.</p>
      <h3>Assignment</h3>
      <p>Below you can find 8 tasks that you need to complete to help us deliver to Benson. Complete as much as you can and if you feel you are
        finished or just don't have knowledge to go any further then ship it to us. If you happen to go beyond these tasks, please document all other improvements so we wouldn't miss them when we grade your solution.</p>
      <h3>How to submit your result</h3>
      <ol>
        <li>Open <b>build.gradle</b> and set your name to variable <b>ext.candidateName</b></li>
        <li>Run: <b>gradle zip</b></li>
        <li>Visit <a href="http://bit.ly/challenge_completed">http://bit.ly/challenge_completed</a> and submit your solution
          <ul>
            <li>The zip file with name "employee-evaluation-app-solution-Your Name.zip" generated to the project directory root by "gradle zip".</li>
          </ul>
        </li>
      </ol>
    </div>
  </div>

  <div class="row">
    <div class="col-md-9">
      <!-- ### Employee and Project names  ### -->
      <nrtl:taskSection sectionId="joinTheNames" heading="1. Joining names">
        <p>In Record.java there are two methdos that previous development team did not finish. Can you write them? The Employee names must be ordered by salary and Project names ordered alphabetically.</p>
      </nrtl:taskSection>
      <!-- ### Bug, what bug ### -->
      <nrtl:taskSection sectionId="fixBug" heading="2. Bug, what bug?">
        <p>Deleting employee and project records does not work sometimes. For example Rigby cannot be deleted. Can you find out why and fix that?</p>
      </nrtl:taskSection>
      <!-- ### Employee start date ### -->
      <nrtl:taskSection sectionId="employeeStartDate" heading="3. Seniority">
        <p>Employees should also have date when they started working in park. Add this field to database, model and to all other relevant classes. The front-end should display date using format dd.mm.yyyy</p>
      </nrtl:taskSection>
      <!-- ### Hard-coded and buggy ### -->
      <nrtl:taskSection sectionId="hardcoded" heading="4. Hard-coded and buggy">
        <p>On statistics page there should be statistics about all the projects but values don't change and some charts are wrong. We believe that some slacker just hardcoded some values.</p>
        <p>There should be following statistics:</p>
        <ul>
          <li>The project with most records - this is simple enough, this is project that is attached to the most records.</li>
          <li>The worst executed project - the project where (VERY_NEGATIVE record count + NEGATIVE record count) - (VERY_POSITIVE record count + POSITIVE
          record count) is greatest</li>
          <li>The best executed project - the project where (VERY_POSITIVE record count + POSITIVE record count) - (VERY_NEGATIVE record count + NEGATIVE
            record count) is greatest</li>
          <li>Employees involved statistics - a bar chart showing number of employees involved in projects</li>
          <li>
            For every project:
            <ul>
              <li>Satisfaction Records - how many VERY_NEGATIVE, NEGATIVE, VERY_POSITIVE or POSITIVE records every project has.</li>
              <li>Employees Records - how many VERY_NEGATIVE, NEGATIVE, VERY_POSITIVE or POSITIVE records every employee involved in a project has.</li>
            </ul>
          </li>
        </ul>
        <p>Developers mumbled something that Controller is completed and some small piece of code still needs to be written...</p>
      </nrtl:taskSection>
      <!-- ### Time reporting ### -->
      <nrtl:taskSection sectionId="timetable" heading="5. Time reporting">
        <p>A simple timetable has been requested to track time, some basic code has been written, your task is to finish it.</p>
        <p>The timetable must show start date, end date and how many hours every employee worked on a project.</p>
      </nrtl:taskSection>
      <!-- ### Who wants backups ### -->
      <nrtl:taskSection sectionId="backup" heading="6. Who wants backups?">
        <p>Benson wants backups. Since the last time Rigby and Mordecai stole the book of records and modified it he is really concerned about security. He wants to have CSV backups of his records. Again, something has been developed but it is very incomplete. The CSV should contain same data as records list.</p>
      </nrtl:taskSection>
      <!-- ### Temp Check ### -->
      <nrtl:taskSection sectionId="tempCheck" heading="7. Temp Check">
        <p>
        Web request is usually handled by single thread that processes it and at some point it also executes the controller method. Project based statistics
        can take long time to query so Rigby suggested Benson that he should demand for software execution temps to get the job done faster. Just like he
        successfully used temps in <a href="https://www.youtube.com/watch?v=0FIxNtv_rQQ">Temp Check</a> episode.
        </p>
        <p>Your task is to execute each statistics returning method call in a separate thread so that database could process them in parallel. These methods include every StatisticsService call from method StatisticsController.getView()</p>
      </nrtl:taskSection>
	    <!-- ### Access logging ### -->
      <nrtl:taskSection sectionId="accessLog" heading="8. Access logging">
        <p>Benson wants to monitor the access to the application and for that he has requested a log file that contains action request information. Please write that code. You can use any output format for log row, but the log must contain basic request data.
        </p>
        <p>Log files should not be large in size since Benson would like to email these logs to his security expert Peeps. As additional feature configure log rotation in a way that new log file is created every hour and no more than 5 files are preserved.</p>
        <p>For bonus points configure or implement a solution for automatically zipping these logs as well.</p>
      </nrtl:taskSection>
    </div>
    <div class="col-md-3">
      <ul class="well nav nav-pills nav-stacked" id="submenu" bs-affix>
        <li style="display: none;" id="templateMenuItem"><a href=""><i class="icon-chevron-right"></i></a></li>
      </ul>
    </div>
  </div>

</nrtl:pageTemplate>


