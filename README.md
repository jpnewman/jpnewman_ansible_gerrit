
# Gerrit Stack

This repo contains a vagrant file to create a Gerrit Stack with OpenLDAP via the following Ansible roles: -

- geerlingguy.firewall
- geerlingguy.security
- geerlingguy.git
- geerlingguy.mysql
- retr0h.logrotate
- bennojoy.openldap_server
- jpnewman.common
- jpnewman.java
- jpnewman.locale-timezone
- jpnewman.gerrit
- jpnewman.apache
- jpnewman.openldap-server

For more information look at the following readmes: -

- ```./Vagrantfiles/MiniStack/README.md```

# Install Vagrant Plugin

~~~bash
vagrant plugin install vagrant-triggers
vagrant plugin install vagrant-cachier
vagrant plugin install vagrant-hostmanager
~~~

# Install Python requirements

~~~bash
sudo pip install -r requirements.txt
~~~

# Install Ansible Roles

~~~bash
ansible-galaxy install -r requirements.yml -p roles
~~~

# Run

### Testing

~~~
ansible-playbook --syntax-check --list-tasks playbook.yml -i .vagrant/provisioners/ansible/inventory/vagrant_ansible_inventory
~~~

## Gerrit Plugins
Plugins can be downloaded from the following URLs and placed in the ```files/plugins/``` folder.

  - <https://storage.cloud.google.com/gerritcodereview-plugins/plugins/master/delete-project/delete-project.jar>
  - <https://storage.cloud.google.com/gerritcodereview-plugins/plugins/master/download-commands/download-commands.jar>
  - <https://storage.cloud.google.com/gerritcodereview-plugins/plugins/master/replication/replication.jar>

## License

MIT / BSD

## Author Information

John Paul Newman
