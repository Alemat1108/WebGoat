<project name="webgoat" default="compile" basedir=".">
    <property name="src" location="src/main/java"/>
    <property name="build" location="build"/>

    <target name="clean">
        <delete dir="${build}"/>
    </target>

    <target name="init">
        <mkdir dir="${build}"/>
    </target>

    <target name="compile" depends="clean,init">
        <javac srcdir="${src}" destdir="${build}"/>
    </target>
</project>




