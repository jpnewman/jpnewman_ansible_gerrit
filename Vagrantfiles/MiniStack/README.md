
# Gerrit MiniStack

The vagrant file it this directory create a mini test stack with the following: -

- Gerrit Server
- OpenLDAP Server

# Requirements

The following Vagrant plugins can be used:

 - [vagrant-cachier](https://github.com/fgrehm/vagrant-cachier)
 - [vagrant-hostmanager](https://github.com/devopsgroup-io/vagrant-hostmanager)

# Versions

- Vagrant 1.8.1
  - vagrant-berkshelf (4.1.0)
  - vagrant-cachier (1.2.1)
  - vagrant-hostmanager (1.8.1)
  - vagrant-omnibus (1.4.1)
  - vagrant-proxyconf (1.5.2)
  - vagrant-share (1.1.5, system)
  - vagrant-triggers (0.5.2)
  - vai (0.9.3)

# Create self-signed unsecured cert

~~~
openssl req -x509 -batch -nodes -newkey rsa:2048 -keyout ../../files/certs/notsecure.key -out ../../files/certs/notsecure.crt -config ../../files/certs/notsecure.cnf -days 1825

openssl x509 -in ../../files/certs/notsecure.crt -text
~~~

# Create Gerrit keys

Both the ```restTokenPrivateKey``` and ```registerEmailPrivateKey``` keys can be created using the following command: -

~~~
openssl rand -base64 36
~~~

# Run

### Vagrant

~~~
vagrant up
~~~

### Specific tags

~~~
ANSIBLE_HOST_KEY_CHECKING=false ansible-playbook playbook.yml -i .vagrant/provisioners/ansible/inventory/vagrant_ansible_inventory --tags "gerrit-server"
~~~

### Testing

~~~
ansible-playbook --syntax-check --list-tasks playbook.yml -i .vagrant/provisioners/ansible/inventory/vagrant_ansible_inventory
~~~

# Boxes

|Box|Description|Vagrant Name|Ansible Host|
|---|---|---|---|---|
|```10.10.10.15```|Gerrit Server|```gerrit```|```gerrit-server```|
|```10.10.10.16```|LDAP Server|```ldap```|```ldap-server```|

# Ports

|Server|Application|Type|Port|
|---|---|---|---|
|```10.10.10.15```|gerrit|TCP|```80```|
|```10.10.10.15```|gerrit|TCP|```443```|
|```10.10.10.16```|ldap|TCP|```636```|
