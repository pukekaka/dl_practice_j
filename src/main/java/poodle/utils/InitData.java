package poodle.utils;



public class InitData {

    private String mode;
    private boolean restore_training;
    private boolean debug;
    private String label_type;
    private Integer n_classes;
    private Integer seq_length;
    private boolean augment;
    private String model;
    private Integer read_head_num;
    private Integer batch_size;
    private Integer num_epoches;
    private Double learning_rate;
    private Integer rnn_size;
    private Integer image_width;
    private Integer image_height;
    private Integer rnn_num_layers;
    private Integer memory_size;
    private Integer memory_vector_dim;
    private Integer shift_range;
    private Integer write_head_num;
    private Integer test_batch_num;
    private Integer n_train_classes;
    private Integer n_test_classes;
    private String save_dir;
    private String tensorboard_dir;
    private Integer output_dim;

    public void setDefault(){
        this.mode = "train";
        this.restore_training = false;
        this.debug = false;
        this.label_type = "one_hot";
        this.n_classes = 5;
        this.seq_length = 50;
        this.augment = true;
        this.model = "MANN";
        this.read_head_num = 4;
        this.batch_size = 16;
        this.num_epoches = 100000;
        this.learning_rate = 1e-3;
        this.rnn_size = 200;
        this.image_width = 20;
        this.image_height = 20;
        this.rnn_num_layers = 1;
        this.memory_size = 128;
        this.memory_vector_dim = 40;
        this.shift_range = 1;
        this.write_head_num = 1;
        this.test_batch_num = 100;
        this.n_train_classes = 1200;
        this.n_test_classes = 423;
        this.save_dir = ".//save//one_shot_learning";
        this.tensorboard_dir = ".//summary//one_shot_learning";
        this.output_dim = null;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isRestore_training() {
        return restore_training;
    }

    public void setRestore_training(boolean restore_training) {
        this.restore_training = restore_training;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getLabel_type() {
        return label_type;
    }

    public void setLabel_type(String label_type) {
        this.label_type = label_type;
    }

    public Integer getN_classes() {
        return n_classes;
    }

    public void setN_classes(Integer n_classes) {
        this.n_classes = n_classes;
    }

    public Integer getSeq_length() {
        return seq_length;
    }

    public void setSeq_length(Integer seq_length) {
        this.seq_length = seq_length;
    }

    public boolean isAugment() {
        return augment;
    }

    public void setAugment(boolean augment) {
        this.augment = augment;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getRead_head_num() {
        return read_head_num;
    }

    public void setRead_head_num(Integer read_head_num) {
        this.read_head_num = read_head_num;
    }

    public Integer getBatch_size() {
        return batch_size;
    }

    public void setBatch_size(Integer batch_size) {
        this.batch_size = batch_size;
    }

    public Integer getNum_epoches() {
        return num_epoches;
    }

    public void setNum_epoches(Integer num_epoches) {
        this.num_epoches = num_epoches;
    }

    public Double getLearning_rate() {
        return learning_rate;
    }

    public void setLearning_rate(Double learning_rate) {
        this.learning_rate = learning_rate;
    }

    public Integer getRnn_size() {
        return rnn_size;
    }

    public void setRnn_size(Integer rnn_size) {
        this.rnn_size = rnn_size;
    }

    public Integer getImage_width() {
        return image_width;
    }

    public void setImage_width(Integer image_width) {
        this.image_width = image_width;
    }

    public Integer getImage_height() {
        return image_height;
    }

    public void setImage_height(Integer image_height) {
        this.image_height = image_height;
    }

    public Integer getRnn_num_layers() {
        return rnn_num_layers;
    }

    public void setRnn_num_layers(Integer rnn_num_layers) {
        this.rnn_num_layers = rnn_num_layers;
    }

    public Integer getMemory_size() {
        return memory_size;
    }

    public void setMemory_size(Integer memory_size) {
        this.memory_size = memory_size;
    }

    public Integer getMemory_vector_dim() {
        return memory_vector_dim;
    }

    public void setMemory_vector_dim(Integer memory_vector_dim) {
        this.memory_vector_dim = memory_vector_dim;
    }

    public Integer getShift_range() {
        return shift_range;
    }

    public void setShift_range(Integer shift_range) {
        this.shift_range = shift_range;
    }

    public Integer getWrite_head_num() {
        return write_head_num;
    }

    public void setWrite_head_num(Integer write_head_num) {
        this.write_head_num = write_head_num;
    }

    public Integer getTest_batch_num() {
        return test_batch_num;
    }

    public void setTest_batch_num(Integer test_batch_num) {
        this.test_batch_num = test_batch_num;
    }

    public Integer getN_train_classes() {
        return n_train_classes;
    }

    public void setN_train_classes(Integer n_train_classes) {
        this.n_train_classes = n_train_classes;
    }

    public Integer getN_test_classes() {
        return n_test_classes;
    }

    public void setN_test_classes(Integer n_test_classes) {
        this.n_test_classes = n_test_classes;
    }

    public String getSave_dir() {
        return save_dir;
    }

    public void setSave_dir(String save_dir) {
        this.save_dir = save_dir;
    }

    public String getTensorboard_dir() {
        return tensorboard_dir;
    }

    public void setTensorboard_dir(String tensorboard_dir) {
        this.tensorboard_dir = tensorboard_dir;
    }

    public Integer getOutput_dim() {
        return output_dim;
    }

    public void setOutput_dim(Integer output_dim) {
        this.output_dim = output_dim;
    }
}
