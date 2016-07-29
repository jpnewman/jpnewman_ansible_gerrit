import com.google.gerrit.sshd.SshCommand
import com.google.gerrit.sshd.CommandMetaData
import com.google.gerrit.extensions.annotations.Export
import com.google.gerrit.server.project.ProjectControl
import com.google.gerrit.server.config.SitePaths
import com.google.gerrit.server.config.GerritServerConfig

import org.eclipse.jgit.lib.Config

import com.google.inject.Inject
import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.Option

@Export("log")
@CommandMetaData(name = "log", description = "git log")
class GitLog extends SshCommand {
  @Inject SitePaths site
  Config serverConfig;

  @Option(name = "--project", aliases = ["-p"], metaVar = "NAME", usage = "project")
  ProjectControl projectControl;

  @Option(name = "--pretty", aliases = [], metaVar = "PRETTY", usage = "pretty")
  String pretty

  @Argument(usage = "commits", metaVar = "COMMITS")
  String commits

  @Inject
  RemoteGit(@GerritServerConfig final Config cfg) {
    serverConfig = cfg;
  }

  def execute(cmd) {
    def home = new File(System.properties.'user.home')
    def proc = new ProcessBuilder(cmd).directory(home).start()

    proc.waitFor()

    def out = proc.getInputStream().getText()
    def err = proc.getErrorStream().getText()
    def exitValue = proc.exitValue()

    return [out , err , exitValue ]
  }

  void run() {
    if (projectControl == null) {
      stdout.println "missing --project name"
      return;
    }
    def basePath = site.resolve(serverConfig.getString("gerrit", null, "basePath"));
    def cmd = ['git',
               "--git-dir=${basePath.getAbsolutePath()}/${projectControl.project.name}.git",
               'log', commits]
    if (pretty)
      cmd += "--pretty=${pretty}"

    def (out, err, exitValue) = execute(cmd as String[])

    if (exitValue == 0) {
      stdout.println out
    } else {
      stdout.println "git stderr: ${err}"
      stdout.println "git exit code: ${exitValue}"
    }
  }
}

commands = [ GitLog ]

