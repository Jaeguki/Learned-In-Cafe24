# Generated by Django 2.2.3 on 2019-07-25 10:57

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('order', '0001_initial'),
        ('item', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='OrderItem',
            fields=[
                ('no', models.BigAutoField(primary_key=True, serialize=False)),
                ('count', models.PositiveIntegerField()),
                ('total_amount', models.PositiveSmallIntegerField()),
                ('item_option_size_no', models.ForeignKey(db_column='item_option_size_no', on_delete=django.db.models.deletion.DO_NOTHING, related_name='order_items', to='item.ItemOptionSize')),
                ('order_no', models.ForeignKey(db_column='order_no', on_delete=django.db.models.deletion.DO_NOTHING, related_name='order_items', to='order.Order')),
            ],
            options={
                'db_table': 'order_item',
            },
        ),
    ]
