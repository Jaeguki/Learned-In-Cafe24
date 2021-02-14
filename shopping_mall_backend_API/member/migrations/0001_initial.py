# Generated by Django 2.2.3 on 2019-07-25 10:56

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Member',
            fields=[
                ('no', models.IntegerField(primary_key=True, serialize=False)),
                ('id', models.CharField(max_length=100)),
                ('email', models.CharField(max_length=255)),
                ('password', models.CharField(max_length=255)),
                ('last_name', models.CharField(max_length=100)),
                ('first_name', models.CharField(max_length=100)),
                ('phone', models.CharField(max_length=100)),
                ('birthday', models.CharField(max_length=10)),
                ('sex', models.BooleanField(default=True)),
                ('register_datetime', models.DateTimeField(auto_now_add=True)),
                ('icon', models.CharField(blank=True, max_length=255, null=True)),
                ('photo', models.CharField(blank=True, max_length=255, null=True)),
                ('update_information_datetime', models.DateTimeField(auto_now=True)),
                ('last_login_datetime', models.DateTimeField(blank=True, null=True)),
                ('seller_cert_yn', models.BooleanField(default=False)),
                ('seller_yn', models.BooleanField(default=False)),
                ('receive_email_yn', models.BooleanField(default=False)),
                ('receive_sms_yn', models.BooleanField(default=False)),
                ('email_cert_yn', models.BooleanField(default=False)),
                ('denied_yn', models.BooleanField(default=False)),
                ('activation_yn', models.BooleanField(default=False)),
            ],
            options={
                'db_table': 'member',
            },
        ),
    ]